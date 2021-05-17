var fgImage=null;
var bgImage=null;

function loadForegroundImage()
{
  var imgFile=document.getElementById("fgfile");
  fgImage=new SimpleImage(imgFile);
  var canvas=document.getElementById("fgcan");
  fgImage.drawTo(canvas);
}

function loadBackgroundImage()
{
  var imgFile=document.getElementById("bgfile");
  bgImage=new SimpleImage(imgFile);
  var canvas=document.getElementById("bgcan");
  bgImage.drawTo(canvas);
}

function greenScreen()
{
  if(fgImage==null || ! fgImage.complete())
  {
      alert("foreground not loaded");
      return;
  }
  if(bgImage==null || ! bgImage.complete())
  {
      alert("background not loaded");
  }
  clearCanvas();
  greenScreenComposite();
}

function clearCanvas()
{
 var fcanvas=document.getElementById("fgcan");
 var fgx = fcanvas.getContext("2d"); 
  fgx.clearRect(0,0,fgImage.getWidth(),fgImage.getHeight()); 
 
 var bcanvas=document.getElementById("bgcan");
 var bgx = bcanvas.getContext("2d");  bgx.clearRect(0,0,fgImage.getWidth(),fgImage.getHeight());
}

function greenScreenComposite()
{
    var output=new SimpleImage(fgImage.getWidth(),fgImage.getHeight());
    for(var pix of fgImage.values())
    {
      var x = pix.getX();
      var y = pix.getY();
      if(pix.getGreen() > pix.getRed() + pix.getBlue())
      {
          var bgPixel=bgImage.getPixel(x,y);
          output.setPixel(x,y,bgPixel);
      }
      else
      {
          output.setPixel(x,y,pix);
      }
    }
    var canvas=document.getElementById("fgcan");
    output.drawTo(canvas);
}