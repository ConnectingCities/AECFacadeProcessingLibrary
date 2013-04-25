
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
  
  float sz=1.0f+12.0f*sin((float)frameCount/3.0f);
  fill(0,255,0);
  
  if(sz<1.0f)
  {
   lastx=x1;
  lasty=y1; 
  }
  else
  {
  
  for(int i=0;i <= (int)sz;i++)
  {
    for(int j=0;j <= (int)sz;j++)
     {
        int xoff=i-(int)sz/2;
        int yoff=j-(int)sz/2;
        
        if(sqrt(xoff*xoff+yoff*yoff)<sqrt(sz))
        {
       
        int x = lastx + xoff;
        int y = lasty + yoff;
        
        rect(x,y,1,1);
        }
     }
  }
  }
  
  //ellipse(x1,y1,2*sz,2*sz);
  //rect(x1,y1,1,5);

 
  
  aec.endDraw();
  aec.drawSides();
}

void keyPressed() {
  aec.keyPressed(key);
}
