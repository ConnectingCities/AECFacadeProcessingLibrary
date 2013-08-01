
AEC aec;

void setup() {
  frameRate(25);
  size(1200, 400);
  
  aec = new AEC();
  aec.init();
}

void draw() {
  aec.beginDraw();
  background(0,0,0);
  noStroke();
  
  fill(255,0,100);
  
  //rect(mouseX/aec.getScaleX(),mouseY/aec.getScaleY(),3,4);

  textSize(6);
  text("word", mouseX/aec.getScaleX(), mouseY/aec.getScaleY()); 

  aec.endDraw();
  aec.drawSides();
}

void keyPressed() {
  aec.keyPressed(key);
}
