package com.example.camilomontoya.menatweapons_cliente;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.UnknownHostException;
import java.util.Observable;

/**
 * Created by CamiloMontoya on 1/03/17.
 */

public class Comunicacion extends Observable implements Runnable {

    public static Comunicacion ref;
    private final String HOST_ADDRESS = "10.0.2.2";
    private final String GROUP_ADDRESS = "226.24.6.8";
    public static int PUERTO = 5000;

    private MulticastSocket mSocket;
    private DatagramSocket dSocket;
    private boolean life;

    private Comunicacion() {
        life = true;
        try {
            dSocket = new DatagramSocket(PUERTO);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Comunicacion getInstance() {
        if (ref == null) {
            ref = new Comunicacion();
            Thread hilo = new Thread(ref);
            hilo.start();
        }
        return ref;
    }

    @Override
    public void run() {
        while (life) {
            if (dSocket != null) {
                DatagramPacket dPacket = recibir();

                if (dPacket != null) {

                    Object data = deserialize(dPacket.getData());

                    setChanged();
                    notifyObservers(data);
                    clearChanged();
                }
            }
        }
        dSocket.close();
    }

    public void enviar(final Object info, final String direccionIP) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                if (dSocket != null) {
                    try {
                        InetAddress hosting = InetAddress.getByName(direccionIP);
                        byte[] buffer = serialize(info);
                        DatagramPacket dPacket = new DatagramPacket(buffer, buffer.length, hosting, 5000);
                        System.out.println("Paquete enviado a: " + direccionIP);

                        dSocket.send(dPacket);
                    } catch (UnknownHostException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }

    private byte[] serialize(Object o) {
        byte[] info = null;
        try {
            ByteArrayOutputStream baOut = new ByteArrayOutputStream();
            ObjectOutputStream oOut = new ObjectOutputStream(baOut);
            oOut.writeObject(o);
            info = baOut.toByteArray();

            oOut.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return info;
    }

    private Object deserialize(byte[] b) {
        Object data = null;
        try {
            ByteArrayInputStream baOut = new ByteArrayInputStream(b);
            ObjectInputStream oOut = new ObjectInputStream(baOut);
            data = oOut.readObject();

            oOut.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return data;
    }


    public DatagramPacket recibir() {
        byte[] buffer = new byte[1024];
        DatagramPacket dPacket = new DatagramPacket(buffer, buffer.length);

        try {
            dSocket.receive(dPacket);
            System.out.println("Paquete recibido de " + dPacket.getAddress());
            return dPacket;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    public String getGROUP_ADDRESS() {
        return GROUP_ADDRESS;
    }
}
