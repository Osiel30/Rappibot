package org.example;

import com.opencsv.CSVWriter;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Rappibot extends TelegramLongPollingBot {
    private Map<Long, String> conversacion = new HashMap<>();
    private Map<Long, String> nombres = new HashMap<>();
    private MenuCafeteria menuCafeteria = new MenuCafeteria();

    @Override
    public String getBotUsername() {
        return "Menusnackbot";
    }

    @Override
    public String getBotToken() {
        return "6581620367:AAFEHc-7ok0vlQzipXmSEo4LqDRP2ahvQCY";
    }

    public void guardarDatosCSV(Long chatId, String nombre, String matricula) {
        String csvFile = "usuarios.csv";
        try (CSVWriter writer = new CSVWriter(new FileWriter(csvFile, true))) {
            String[] data = {chatId.toString(), nombre, matricula};
            writer.writeNext(data);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onUpdateReceived(Update update) {
        String message = update.getMessage().getText();
        Long chatId = update.getMessage().getChatId();

        if (message.equals("/start")) {
            sendMessage(generateSendMessage(chatId, "¡Bienvenido! Mi nombre es Rappibot y voy a ayudarte a levantar tu orden."));
            conversacion.put(chatId, "/start");
            sendMessage(generateSendMessage(chatId, "Por favor, ingresa tu nombre: "));
        } else if (conversacion.get(chatId) != null && conversacion.get(chatId).equals("/start")) {
            nombres.put(chatId, message); // Guardar el nombre
            conversacion.put(chatId, "nombre_ingresado");
            sendMessage(generateSendMessage(chatId, "Ahora ingresa tu matrícula: "));
        } else if (conversacion.get(chatId) != null && conversacion.get(chatId).equals("nombre_ingresado")) {
            String nombre = nombres.get(chatId);
            guardarDatosCSV(chatId, nombre, message);
            conversacion.remove(chatId);
            nombres.remove(chatId);

            sendMessage(generateSendMessage(chatId, "Gracias por proporcionar tus datos."));
            sendMessage(generateSendMessage(chatId, "Ahora, elige a qué cafetería deseas entrar:"));
            sendMessage(generateSendMessage(chatId, "/Cafeteria_Jaguares"));
            sendMessage(generateSendMessage(chatId, "/Cafeteria_Upv"));
            conversacion.put(chatId, "elige_cafeteria");
        } else if (conversacion.get(chatId) != null && conversacion.get(chatId).equals("elige_cafeteria")) {
            if (message.equals("/Cafeteria_Jaguares")) {
                enviarComandosMenu(chatId, "Cafeteria_Jaguares"); // Pasar el nombre correcto
            } else if (message.equals("/Cafeteria_Upv")) {
                enviarComandosMenu(chatId, "Cafeteria_Upv"); // Pasar el nombre correcto
            } else {
                sendMessage(generateSendMessage(chatId, "Opción no válida. Por favor, elige una cafetería válida."));
            }
            conversacion.remove(chatId);
        }
    }

    public void enviarComandosMenu(Long chatId, String cafeteria) {
        // Obtener los comandos del menú para la cafetería especificada
        String[] comandosMenu = menuCafeteria.obtenerMenu(cafeteria);

        // Enviar los comandos al usuario
        StringBuilder mensaje = new StringBuilder("Elige un producto:\n");
        for (String comando : comandosMenu) {
            mensaje.append(comando).append("\n");
        }

        // Enviar el mensaje al usuario
        sendMessage(generateSendMessage(chatId, mensaje.toString()));
    }

    private SendMessage generateSendMessage(Long chatId, String message) {
        return new SendMessage(chatId.toString(), message);
    }

    private void sendMessage(SendMessage sendMessage) {
        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}
