package org.aec.facade;

import java.awt.Color;
import java.net.*;

public class FrameBuffer
{

    public FrameBuffer()
    {
        frameBuffer = new byte[nrAddresses * packageSize];
        port = 8080;
        for(int address = 0; address < nrAddresses; address++)
        {
            frameBuffer[address * 5 + offAddressLo] = (byte)(address % 256);
            frameBuffer[address * 5 + offAddressHi] = (byte)(address / 256);
            frameBuffer[address * 5 + offsetRed] = 0;
            frameBuffer[address * 5 + offsetGreen] = 0;
            frameBuffer[address * 5 + offsetBlue] = 0;
        }

        try
        {
            this.address = InetAddress.getByName("127.0.0.1");
        }
        catch(UnknownHostException e)
        {
            e.printStackTrace();
        }
    }

    public void setIp(InetAddress adr)
    {
        address = adr;
    }

    public void setPort(int po)
    {
        port = po;
    }

    public void setColor(Color color)
    {
        for(int address = 0; address < nrAddresses; address++)
        {
            frameBuffer[address * packageSize + offsetRed] = (byte)color.getRed();
            frameBuffer[address * packageSize + offsetGreen] = (byte)color.getGreen();
            frameBuffer[address * packageSize + offsetBlue] = (byte)color.getBlue();
        }

    }

    public void setColor(int address, Color color)
    {
        if(address >= 0 && address < nrAddresses)
        {
            frameBuffer[address * packageSize + offsetRed] = (byte)color.getRed();
            frameBuffer[address * packageSize + offsetGreen] = (byte)color.getGreen();
            frameBuffer[address * packageSize + offsetBlue] = (byte)color.getBlue();
        }
    }

    public void flush()
    {
        try
        {
            DatagramPacket packet = new DatagramPacket(frameBuffer, frameBuffer.length, address, port);
            DatagramSocket socket = new DatagramSocket();
            socket.send(packet);
        }
        catch(Exception e)
        {
            System.out.println((new StringBuilder("Exception when sending framebuffer: ")).append(e.getMessage()).toString());
        }
    }

    public int getPort()
    {
        return port;
    }

    public String getIp()
    {
        return address.getHostAddress();
    }

    private final int packageSize = 5;
    private final int nrAddresses = 1085;
    private final int offAddressLo = 0;
    private final int offAddressHi = 1;
    private final int offsetRed = 2;
    private final int offsetGreen = 3;
    private final int offsetBlue = 4;    
    private byte frameBuffer[];
    private InetAddress address;
    private int port;
}
