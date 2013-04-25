package org.aec.facade;

import java.awt.Color;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Iterator;

public class AECPlugin
{

    public AECPlugin()
    {
        fw = new FileHandler();
        scale = 8;
        frameWidth = 100;
        activeSide = null;
        allSides = false;
        activeColor = new Color(255, 255, 255, 200);
        inactiveColor = new Color(255, 255, 255, 80);
        frame.setColor(Color.black);
        frame.flush();
    }

    public void init()
    {
        Side side;
        for(Iterator iterator = building.getSides().iterator(); iterator.hasNext(); )
        {
            side = (Side)iterator.next();
            side.setColor(inactiveColor);
        }
    }

    public void setActiveColor(Color c)
    {
        activeColor = c;
    }

    public void setInActiveColor(Color c)
    {
        inactiveColor = c;
    }

    public void setFrameWidth(int fw)
    {
        frameWidth = fw;
    }

    public void loadConfig()
    {
        String fileTextArray[] = fw.load();
        if(fileTextArray == null)
            return;
        for(int i = 0; i < fileTextArray.length; i++)
        {
            String temp = fileTextArray[i];
            if(temp.contains("Scale"))
            {
                int index = temp.indexOf("#");
                temp = temp.substring(index + 1, temp.length());
                scale = Integer.parseInt(temp);
            } else
            if(temp.contains("Port"))
            {
                int index = temp.indexOf("#");
                temp = temp.substring(index + 1, temp.length());
                frame.setPort(Integer.parseInt(temp));
            } else
            if(temp.contains("Ip"))
            {
                int index = temp.indexOf("#");
                temp = temp.substring(index + 1, temp.length());
                try
                {
                    InetAddress adr = InetAddress.getByName(temp);
                    frame.setIp(adr);
                }
                catch(UnknownHostException e)
                {
                    e.printStackTrace();
                }
            } else
            {
                int index = temp.indexOf("#");
                String tempRest = temp.substring(index + 1, temp.length());
                temp = temp.substring(0, index);
                index = tempRest.indexOf("#");
                int x = Integer.parseInt(tempRest.substring(0, index));
                int y = Integer.parseInt(tempRest.substring(index + 1, tempRest.length()));
                setSideOffset(temp, x, y);
            }
        }

        System.out.println("LOADING COMPLETED");
    }

    public void saveConfig()
    {
        String saveText = new String();
        saveText = (new StringBuilder(String.valueOf(saveText))).append("Scale#").append(scale).append("\n").toString();
        saveText = (new StringBuilder(String.valueOf(saveText))).append("Port#").append(frame.getPort()).append("\n").toString();
        saveText = (new StringBuilder(String.valueOf(saveText))).append("Ip#").append(frame.getIp()).append("\n").toString();
        for(Iterator iterator = building.getSides().iterator(); iterator.hasNext();)
        {
            Side side = (Side)iterator.next();
            String name = side.getName();
            name = name.toUpperCase();
            name = name.replace(" ", "_");
            saveText = (new StringBuilder(String.valueOf(saveText))).append(name).append("#").append(side.x).append("#").append(side.y).append("\n").toString();
        }

        fw.save(saveText);
    }

    public void keyPressed(int value, int keyCode)
    {
        if(value == 115 || value == 83)
        {
            System.out.println("----SAVED CHANGES----");
            saveConfig();
            return;
        }
        if(value == 82)
        {
            if(scale < 20)
                scale++;
            System.out.println((new StringBuilder("CHANGED SCALE TO ")).append(scale).toString());
        }
        if(value == 114)
        {
            if(scale > 1)
                scale--;
            System.out.println((new StringBuilder("CHANGED SCALE TO ")).append(scale).toString());
            return;
        }
        if(value >= 48 && value <= 57)
        {
            changeSide(value);
            return;
        }
        if(activeSide != null)
            switch(keyCode)
            {
            case 38: // '&'
                activeSide.moveUp();
                break;

            case 37: // '%'
                activeSide.moveLeft();
                break;

            case 39: // '\''
                activeSide.moveRight();
                break;

            case 40: // '('
                activeSide.moveDown();
                break;
            }
        if(allSides)
            switch(keyCode)
            {
            default:
                break;

            case 38: // '&'
            {
                Side side;
                for(Iterator iterator = building.getSides().iterator(); iterator.hasNext(); side.moveUp())
                    side = (Side)iterator.next();
            }
                break;

            case 37: // '%'
            {
                Side side;
                for(Iterator iterator1 = building.getSides().iterator(); iterator1.hasNext(); side.moveLeft())
                    side = (Side)iterator1.next();
            }
                break;

            case 39: // '\''
            {
                Side side;
                for(Iterator iterator2 = building.getSides().iterator(); iterator2.hasNext(); side.moveRight())
                    side = (Side)iterator2.next();
            }
                break;

            case 40: // '('
            {
                Side side;
                for(Iterator iterator3 = building.getSides().iterator(); iterator3.hasNext(); side.moveDown())
                    side = (Side)iterator3.next();
            }
                break;
            }
    }

    void changeSide(int value)
    {
        if(activeSide != null)
            activeSide.setColor(inactiveColor);
        if(allSides)
        {
            Side side;
            for(Iterator iterator = building.getSides().iterator(); iterator.hasNext(); side.setColor(inactiveColor))
                side = (Side)iterator.next();

        }
        activeSide = null;
        allSides = false;
        if(value == 48)
        {
            System.out.println("NO SELECTION");
            return;
        }
        if(value == 57)
        {
            allSides = true;
            Side side;
            for(Iterator iterator1 = building.getSides().iterator(); iterator1.hasNext(); side.setColor(activeColor))
                side = (Side)iterator1.next();

            System.out.println("ALL SELECTED");
            return;
        } else
        {
            int side = value - 48 - 1;
            activeSide = getSide(Building.SIDE.values()[side]);
            System.out.println((new StringBuilder("CHANGED ACTIVE SIDE TO ")).append(activeSide.name).toString());
            activeSide.setColor(activeColor);
            return;
        }
    }

    public void setSideOffset(String side, int x, int y)
    {
        Building.SIDE s = Building.SIDE.valueOf(side);
        building.getSide(s).setOffset(x, y);
    }

    public Side getSide(String side)
    {
        Building.SIDE s = Building.SIDE.valueOf(side);
        return building.getSide(s);
    }

    public Side getSide(Building.SIDE side)
    {
        return building.getSide(side);
    }

    public java.util.List getSides()
    {
        return building.getSides();
    }

    public void update(int pixels[])
    {
        Side side;
        for(Iterator iterator = building.getSides().iterator(); iterator.hasNext(); side.update(frame, pixels, scale, frameWidth))
            side = (Side)iterator.next();

        frame.flush();
    }

    private static FrameBuffer frame = new FrameBuffer();
    private static Building building = new Building();
    private FileHandler fw;
    public int scale;
    protected int frameWidth;
    protected Side activeSide;
    protected boolean allSides;
    protected Color activeColor;
    protected Color inactiveColor;

}
