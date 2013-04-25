
AEC aec;
int lastx=0;
int lasty=0;

void setup() {
  frameRate(25);
  size(1200, 400);
  
  aec = new AEC();
  aec.init();
}

void draw() {
  aec.beginDraw();

  // DRAW CODE
  int x1 = mouseX / aec.getScaleX();
  int y1 = mouseY / aec.getScaleY();
  int x2 = (frameCount / 3) % 55;
  
  background(0, 0,0);
  
  println(x1 + " " + y1);

  noStroke();

  int step = 1;
  
  float fsize = (1.0f+12.0f*sin((float)frameCount/3.0f));
  int sz= (int) fsize;
  int radius = (int)(fsize/2.0f);
  fill(0,255,0);
  
  if(sz<1.0f)
  {
     lastx=x1;
     lasty=y1; 
  }
  else
  {
  
    for(int i=0;i <= sz;i++)
    {
      for(int j=0;j <= sz;j++)
      {
          int dx=i-radius;
          int dy=j-radius;
          
          if(sqrt(dx*dx+dy*dy)<sqrt( radius*radius ))
          {
         
          int x = lastx + dx;
          int y = lasty + dy;
          
          rect(x,y,1,1);
          }
       }
    }
  }
  aec.endDraw();
  aec.drawSides();
}

void keyPressed() {
  aec.keyPressed(key);
}
