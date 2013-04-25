package org.aec.facade;

import java.awt.Color;

public abstract class Side
{

    public Side()
    {
        x = 0;
        y = 0;
        color = new Color(100, 100, 100);
        pixelWidth = 1;
        pixelHeight = 1;
    }

    public String getName()
    {
        return name;
    }

    public void setOffset(int x_, int y_)
    {
        x = x_;
        y = y_;
    }

    public int getX()
    {
        return x;
    }

    public int getY()
    {
        return y;
    }

    public Color getColor()
    {
        return color;
    }

    public void setColor(Color color_)
    {
        color = color_;
    }

    public int getPixelWidth()
    {
        return pixelWidth;
    }

    public int getPixelHeight()
    {
        return pixelHeight;
    }

    public int[][] getWindowAddress()
    {
        return windowAddrs;
    }

    public int[] getOffset()
    {
        int out[] = {
            x, y
        };
        return out;
    }

    public int getStartAddress()
    {
        return startAddr;
    }

    public int getEndAddress()
    {
        return endAddr;
    }

    public int getStartRow()
    {
        return startRow;
    }

    public int getEndRow()
    {
        return endRow;
    }

    public int getNrColumns()
    {
        return nrColumns;
    }

    public int getAddress(int row, int column)
    {
        if(row >= startRow && row <= endRow && column >= 0 && column < nrColumns)
            return windowAddrs[row - startRow][column];
        else
            return -1;
    }

    public void setColor(FrameBuffer frame, Color color)
    {
        for(int address = getStartAddress(); address <= getEndAddress(); address++)
            frame.setColor(address, color);

    }

    public void setRowColor(FrameBuffer frame, int row, Color color)
    {
        if(row >= getStartRow() && row <= getEndRow())
        {
            for(int column = 0; column < getNrColumns(); column++)
                frame.setColor(getAddress(row, column), color);

        }
    }

    public void setColumnColor(FrameBuffer frame, int column, Color color)
    {
        if(column >= 0 && column < getNrColumns())
        {
            for(int row = getStartRow(); row <= getEndRow(); row++)
                frame.setColor(getAddress(row, column), color);

        }
    }

    public void setWindowColor(FrameBuffer frame, int row, int column, Color color)
    {
        frame.setColor(getAddress(row, column), color);
    }

    public void update(FrameBuffer frame, int pixels[], int scale, int width)
    {
        for(int j = 0; j < windowAddrs.length; j++)
        {
            for(int i = 0; i < windowAddrs[j].length; i++)
                if(windowAddrs[j][i] > -1)
                {
                    int pos = (x + i * pixelWidth) * scale + (y + j * pixelHeight) * scale * width;
                    if(pos < pixels.length)
                    {
                        int value = pixels[pos];
                        Color color = new Color(value);
                        frame.setColor(getAddress(getStartRow() + j, i), color);
                    }
                }

        }

    }

    public void moveUp()
    {
        if(y <= 0)
        {
            return;
        } else
        {
            y--;
            return;
        }
    }

    public void moveDown()
    {
        y++;
    }

    public void moveLeft()
    {
        if(x <= 0)
        {
            return;
        } else
        {
            x -= 2;
            return;
        }
    }

    public void moveRight()
    {
        x += 2;
    }

    protected String name;
    protected int startAddr;
    protected int endAddr;
    protected int startRow;
    protected int endRow;
    protected int nrColumns;
    protected int windowAddrs[][];
    protected int x;
    protected int y;
    protected Color color;
    protected int pixelWidth;
    protected int pixelHeight;
}
