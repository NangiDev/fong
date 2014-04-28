import Image
import sys
import math

def main():
	picture = "logotype"						#name of the picture without file extension
	img = Image.open(picture + ".png")		#open requested image with file extension. Change ".png" for other file type
	norm = Image.new("RGBA", img.size)		#Creates a new image that will be loaded with RGBA from normals
	pix = img.load()						#Loads the requested image into an list for faster access to pixels

	print ("image loaded")

	Zmatrix = [[0 for y in xrange(img.size[1])] for x in xrange(img.size[0])]		#Creates an matrix by the same dimensions as the requseted image. Will be loaded with the Z-coordinates
	findEdges(img, pix, Zmatrix)
	print ("edges found")
	generateZ(Zmatrix, img)
	print ("Z generated")

	XYmatrix = fillXYwithCoords(img)
	getXandY(Zmatrix, XYmatrix, img)

	d = 255/getBiggest(Zmatrix, img)

	for x in xrange(img.size[0]):
		print(XYmatrix[x])

	for y in xrange(img.size[1]):
		for x in xrange(img.size[0]):
			(q,w) = XYmatrix[x][y]
			a = (255 - (q*0.5+0.5)*255)
			b = (w*0.5+0.5)*255
			c = 255 - (Zmatrix[x][y])
			norm.putpixel((x,y), (int(a), int(b), int(c), 255))

	norm.save("normals/" + picture + "Normal.png")

def getBiggest(Zmatrix, img):
	biggest = 0
	for y in xrange(img.size[1]):
		for x in xrange(img.size[0]):
			if Zmatrix[x][y] > biggest:
				biggest = Zmatrix[x][y]
	return biggest

def getXandY(Zmatrix, XYmatrix, img):
	for y in range(0,img.size[1]):
		for x in range(0,img.size[0]):
			if Zmatrix[x][y] == 0:
				XYmatrix[x][y] = (0,0)
			else:
				distanceCalc(XYmatrix, Zmatrix, img, x, y)

def distanceCalc(XYmatrix, Zmatrix, img, x, y):
	coords = getCoordsBiggest(Zmatrix, img, x, y)
	coords = (coords[0]-x, coords[1]-y)
	XYmatrix[x][y] = normalize(coords[0], coords[1])

def getCoordsBiggest(Zmatrix, img, x, y):
	biggest = 0
	for y in xrange(img.size[1]):
		for x in xrange(img.size[0]):
			if Zmatrix[x][y] > biggest:
				biggest = Zmatrix[x][y]
				coords = (x,y)
	return coords

def normalize(x,y):
	length = math.sqrt(x*x + y*y)
	return (float(x) / max(length, 0.1), float(y) / max(length, 0.1))

def fillXYwithCoords(img):
	XYmatrix = [[0 for y in xrange(img.size[1])] for x in xrange(img.size[0])]
	for y in range(0,img.size[1]):
		for x in range(0,img.size[0]):
			XYmatrix[x][y] = (x,y)
	return XYmatrix

def findEdges(img, pix, Zmatrix):
	for y in xrange(img.size[1]):
		for x in xrange(img.size[0]):
			pixel = pix[x, y][3]
			if pixel == 0:
				Zmatrix[x][y] = 0
			else:
				Zmatrix[x][y] = sys.maxint
			if (x == 0 or x == img.size[0]-1 or y == 0 or y == img.size[1]-1) and not pixel == 0:
				Zmatrix[x][y] = 1

def generateZ(Zmatrix, img):
	for y in range(1, img.size[1]-1):
		for x in range(1, img.size[0]-1):
			u = img.size[0]-1 - x
			v = img.size[1]-1 - y
			lowest = getSmallestNeighbour(Zmatrix, u, v)
			if lowest < Zmatrix[u][v]:
				Zmatrix[u][v] = lowest + 1
			else:
				continue
	for y in range(1, img.size[1]-1):
		for x in range(1, img.size[0]-1):
			lowest = getSmallestNeighbour(Zmatrix, x, y)
			if lowest < Zmatrix[x][y]:
				Zmatrix[x][y] = lowest + 1
			else:
				continue

def getSmallestNeighbour(Zmatrix, x, y):
	lowest = sys.maxint
	for u in range(-1,2):
		for v in range(-1,2):
			if (Zmatrix[x+v][y+u] < lowest):
				lowest = Zmatrix[x+v][y+u]
	return lowest

main()