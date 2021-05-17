var canvas = document.getElementById("canvas"),
	originalImage = null,
  imageBlur = null,
	imageGrey = null,
	imageRed = null,
	imageRainbow = null;

function loadImage() {
	var file = document.getElementById("file");
	originalImage = new SimpleImage(file);
	imageGrey = new SimpleImage(file);
	imageRed = new SimpleImage(file);
	imageRainbow = new SimpleImage(file);
	imageBlur = new SimpleImage(file);
	originalImage.drawTo(canvas);
}

function doBlur() 
{ 
    for (var pixel of imageBlur.values()) 
    {
      var x = pixel.getX();
      var y = pixel.getY();
      if (Math.random() > 0.5) 
      {
        var other = getPixelNearby(imageBlur, x, y, 5);
        imageBlur.setPixel(x, y, other);
      }
    }
    imageBlur.drawTo(canvas);
}

function getPixelNearby (imageBlur, x, y, radius) 
{
  var dx = (Math.random() * 2 * radius) - radius;
  var dy = (Math.random() * 2 * radius) - radius;
  var nx = ensureInImage(x + dx, imageBlur.getWidth());
  var ny = ensureInImage(y + dy, imageBlur.getHeight());
  return imageBlur.getPixel(nx, ny);
}

function ensureInImage (coordinate, size) 
{
  if (coordinate < 0) 
  {
    return 0;
  }
  if (coordinate >= size) 
  {
    return size - 1;
  }
  return coordinate;
}

function filterGrey() {
	// Reset Image
	for(var pixel of imageGrey.values()) {
		var originalPixel = originalImage.getPixel(pixel.getX(), pixel.getY());
		imageGrey.setPixel(pixel.getX(), pixel.getY(), originalPixel)
	}
	
	// Grey Filter
	for(var pixel of imageGrey.values()) {
		var avg = (pixel.getRed() + pixel.getGreen() + pixel.getBlue()) / 3;
		pixel.setRed(avg);
		pixel.setGreen(avg);
		pixel.setBlue(avg);
	}
	imageGrey.drawTo(canvas);
}

function printOriginal(img) {
	img.drawTo(canvas);
}

function makeRed() {
	// Reset Image
	for(var pixel of imageRed.values()) {
		var originalPixel = originalImage.getPixel(pixel.getX(), pixel.getY());
		imageRed.setPixel(pixel.getX(), pixel.getY(), originalPixel)
	}
	
	// Red Filter 	
	for (var pixel of imageRed.values()) {
		var avg = (pixel.getRed() + pixel.getGreen() + pixel.getBlue()) / 3;
		if(avg < 128) {
			pixel.setRed(avg * 2 - 255);
			pixel.setBlue(avg * 2 - 255);
		} else {
			pixel.setRed(255);
			pixel.setGreen(avg * 2 - 255);
			pixel.setBlue(avg * 2 - 255);
		}
	}
	imageRed.drawTo(canvas);
}

function makeRainbow() {
	// Reset Image
	for(var pixel of imageRainbow.values()) {
		var originalPixel = originalImage.getPixel(pixel.getX(), pixel.getY());
		imageRainbow.setPixel(pixel.getX(), pixel.getY(), originalPixel)
	}
	
	// Rainbow Filter
	for(var pixel of imageRainbow.values()) {
		var avg = (pixel.getRed() + pixel.getGreen() + pixel.getBlue()) / 3;
		
		/* ---- Red Strip ----- */
		if(pixel.getY() <= imageRainbow.getHeight() * (1/7)) {
			if(avg < 128) {
				pixel.setRed(2 * avg);
				pixel.setGreen(0);
				pixel.setBlue(0);
			} else {
				pixel.setRed(255);
				pixel.setGreen(avg * 2 - 255);
				pixel.setBlue(avg * 2 - 255);
			}
		}
		
		/* ----- Orange Stripe ----- */
		if(pixel.getY() > imageRainbow.getHeight() * (1/7) && pixel.getY() <= imageRainbow.getHeight() * (2/7)) {
			if(avg < 128) {
				pixel.setRed(2 * avg);
				pixel.setGreen(0.8 * avg);
				pixel.setBlue(0);
			} else {
				pixel.setRed(255);
				pixel.setGreen(1.2 * avg - 51);
				pixel.setBlue(avg * 2 - 255);
			}
		}
		
		/* ----- Yellow Strip ----- */
		if(pixel.getY() > imageRainbow.getHeight() * (2/7) && pixel.getY() <= imageRainbow.getHeight() * (3/7)) {
			if(avg < 128) {
				pixel.setRed(2 * avg);
				pixel.setGreen(2 * avg);
				pixel.setBlue(0);
			} else {
				pixel.setRed(255);
				pixel.setGreen(255);
				pixel.setBlue(avg * 2 - 255);
			}
		}
		
		/* ----- Green Strip ----- */
		if(pixel.getY() > imageRainbow.getHeight() * (3/7) && pixel.getY() <= imageRainbow.getHeight() * (4/7)) {
			if(avg < 128) {
				pixel.setRed(0);
				pixel.setGreen(2 * avg);
				pixel.setBlue(0);
			} else {
				pixel.setRed(avg * 2 - 255);
				pixel.setGreen(255);
				pixel.setBlue(avg * 2 - 255);
			}
		}
		
		/* ----- Blue Strip ----- */
		if(pixel.getY() > imageRainbow.getHeight() * (4/7) && pixel.getY() <= imageRainbow.getHeight() * (5/7)) {
			if(avg < 128) {
				pixel.setRed(0);
				pixel.setGreen(0);
				pixel.setBlue(2 * avg);
			} else {
				pixel.setRed(avg * 2 - 255);
				pixel.setGreen(avg * 2 - 255);
				pixel.setBlue(255);
			}
		}
		
		/* ----- Indigo Strip ----- */
		if(pixel.getY() > imageRainbow.getHeight() * (5/7) && pixel.getY() <= imageRainbow.getHeight() * (6/7)) {
			if(avg < 128) {
				pixel.setRed(0.8 * avg);
				pixel.setGreen(0);
				pixel.setBlue(2 * avg);
			} else {
				pixel.setRed(1.2 * avg - 51);
				pixel.setGreen(avg * 2 - 255);
				pixel.setBlue(255);
			}
		}
		
		/* ----- Violet Strip ----- */
		if(pixel.getY() > imageRainbow.getHeight() * (6/7) && pixel.getY() <= imageRainbow.getHeight() * (7/7)) {
			if(avg < 128) {
				pixel.setRed(1.6 * avg);
				pixel.setGreen(0);
				pixel.setBlue(1.6 * avg);
			} else {
				pixel.setRed(0.4 * avg + 153);
				pixel.setGreen(avg * 2 - 255);
				pixel.setBlue(0.4 * avg + 153);
			}
		}
		
	}
	imageRainbow.drawTo(canvas);
}

function makeGrey() {
	if(imageGrey != null) {
		filterGrey();
	}
}

function resetImage() {
	var context = canvas.getContext("2d");
	context.clearRect(0, 0, canvas.width, canvas.height);
	printOriginal(originalImage);
}