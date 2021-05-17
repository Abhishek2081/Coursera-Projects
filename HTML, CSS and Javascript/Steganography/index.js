window.CP.PenTimer.MAX_TIME_IN_LOOP_WO_EXIT = 6000;
var image1;
var image1cropped;
var image2;
var image2cropped;
var imagewithsecret;
var canvas1;
var canvas2;
var canvas3;

function upload1() {
canvas1 = document.getElementById ("C1");
var img1 = document.getElementById ("pic1");
image1 = new SimpleImage (img1);
image1.drawTo(canvas1);
}

function upload2() {
canvas2 = document.getElementById ("C2");
var img2 = document.getElementById ("pic2");
image2 = new SimpleImage (img2);
image2.drawTo(canvas2);
}

function doCropimage1() {
crop1 (image1,700,700);
image1cropped.drawTo(canvas1);
}  

function doCropimage2() {
crop2 (image2,700,700);
image2cropped.drawTo(canvas2);
} 

function crop1(image, width, height) {
  image1cropped = new SimpleImage (width, height);
    for (var pixel of image1cropped.values()){
      var x = pixel.getX();
      var y = pixel.getY();
      var uncrpix = image.getPixel(x,y);
      pixel.setRed(uncrpix.getRed());
      pixel.setGreen(uncrpix.getGreen());
      pixel.setBlue(uncrpix.getBlue());
      pixel.setAlpha(uncrpix.getAlpha());
    }
  return image1cropped;
  }

function crop2(image, width, height) {
  image2cropped = new SimpleImage (width, height);
    for (var pixel of image2cropped.values()){
      var x = pixel.getX();
      var y = pixel.getY();
      var uncrpix = image.getPixel(x,y);
      pixel.setRed(uncrpix.getRed());
      pixel.setGreen(uncrpix.getGreen());
      pixel.setBlue(uncrpix.getBlue());
      pixel.setAlpha(uncrpix.getAlpha());
    }
  return image2cropped;
}

function clearpixel(color) {
  var x = Math.floor(color/16)*16; 
  return x;
}

function chop2Hide (image) {  
  for (var pixel of image.values()) {
   pixel.setRed(clearpixel(pixel.getRed()));
   pixel.setGreen(clearpixel(pixel.getGreen()));
   pixel.setBlue(clearpixel(pixel.getBlue()));
 }
  return image;
} 
 
function CHOP1 () {
  chop2Hide (image1cropped);
  image1cropped.drawTo(canvas1);
}

function CHOP2 () {
  chop2Hide (image2cropped);
  image2cropped.drawTo(canvas2);
}


function shiftpixel(color) {
  var x = Math.floor(color/16); 
  return x;
}

function shift2Hide (image) {  
  for (var pixel of image.values()) {
   pixel.setRed(shiftpixel(pixel.getRed()));
   pixel.setGreen(shiftpixel(pixel.getGreen()));
   pixel.setBlue(shiftpixel(pixel.getBlue()));
 }
  return image;
 } 

function SHIFT1(){
  shift2Hide (image1cropped);
  image1cropped.drawTo(canvas1);
}

function SHIFT2 () {
  shift2Hide (image2cropped);
  image2cropped.drawTo(canvas2);
} 

function summ(f,s) {
  var summa = f+s;
  return summa;  
}

function combine(firstimage,secondimage) {
  canvas3 = document.getElementById ("C3");
  imagewithsecret = new SimpleImage (700,700);
  for (var pixel of imagewithsecret.values()) {
   var x = pixel.getX();
   var y = pixel.getY();
   var pixel1 = firstimage.getPixel(x,y);
   var pixel2 = secondimage.getPixel(x,y);
    pixel.setRed(summ (pixel1.getRed(),pixel2.getRed()));
    pixel.setGreen(summ (pixel1.getGreen(),pixel2.getGreen()));
    pixel.setBlue(summ (pixel1.getBlue(),pixel2.getBlue()));   
  }
  return imagewithsecret;
}

function docombine() {
  combine(image1cropped,image2cropped);
  imagewithsecret.drawTo(canvas3);
}