
AEC aec;
PFont font1;

// some parameters that turned out to work best for the font we're using
float FONT_SIZE = 6;
float FONT_OFFSET_Y = 0.12;
float FONT_SCALE_X = 2.669;
float FONT_SCALE_Y = 2.67;

String string = "hello WORLD";

void setup() {
  frameRate(25);
  size(1200, 400);
  
  // NOTE: This font needs to be in the data folder. It's actually called "Proggy Square (Slashed Zero)", 
  // and it's available for free at http://www.proggyfonts.com
  // You COULD use a different font, but you'd have to tune the above parameters. Monospaced bitmap fonts work best.
  font1 = createFont("facade.ttf", 9, false);
  
  aec = new AEC();
  aec.init();
  
  frameRate(30);
  
  
}

void draw() {
  aec.beginDraw();
  background(0,0,0);
  noStroke();
  
  fill(255,0,100);
  
  // determines the speed (number of frames between text movements)
  int frameInterval = 4;
  
  // min and max grid positions at which the text origin should be. we scroll from max (+40) to min (-80)
  int minPos = -80;
  int maxPos = 40;
  int loopFrames = (maxPos-minPos) * frameInterval;
  
  // vertical grid pos
  int yPos = 15;
  
  displayText(max(minPos, maxPos - (frameCount%loopFrames) / frameInterval), yPos);

  aec.endDraw();
  aec.drawSides();
}

void displayText(int x, int y)
{
  // push & translate to the text origin
  pushMatrix();
  translate(x, y+FONT_OFFSET_Y);
  
  // scale the font up by fixed paramteres so it fits our grid
  scale(FONT_SCALE_X,FONT_SCALE_Y);
  textFont(font1);
  textSize(FONT_SIZE);
  
  // draw the font glyph by glyph, because the default kerning doesn't align with our grid
  for(int i = 0; i < string.length(); i++)
  {
    text(string.charAt(i), (float)i*3, 0);
  }
  
  popMatrix();
  
}

void keyPressed() {
  aec.keyPressed(key);
}
