import Image
import sys
import math

def main():
	picture = "enemyBlue1"						#name of the picture without file extension
	img = Image.open(picture + ".png")		#open requested image with file extension. Change ".png" for other file type
	norm = Image.new("RGBA", img.size)		#Creates a new image that will be loaded with RGBA from normals
	pix = img.load()						#Loads the requested image into an list for faster access to pixels

	Zmatrix = [[0 for y in xrange(img.size[1])] for x in xrange(img.size[0])]		#Creates an matrix by the same dimensions as the requseted image. Will be loaded with the Z-coordinates
	XYmatrix = fillXYwithCoords(img)												#fillXYwithCoords does the same as the row above, but instead of integer it fills it with tuples (0,0)

	findEdges(Zmatrix, pix, img)			#determine which pixels are transparent and not. Transparent pixels will get Z-value = 0 and non-transparent will get Z-value = maxInteger.
	generateZ(Zmatrix, img)					#Generate the Z-values by looking att the lowest neighbour and set value to lowest neighbour+1.
	getXandY(Zmatrix, XYmatrix, img)

	for y in range(1,img.size[1]-1):
		for x in range(1,img.size[0]-1):
			a = smoothXY(XYmatrix, pix, x, y)

	d = 255/getBiggest(Zmatrix, img)

	#for x in xrange(img.size[0]):
		#print(Zmatrix[x])

	for y in xrange(img.size[1]):
		for x in xrange(img.size[0]):
			(q,w) = XYmatrix[x][y]
			a = ((q*0.5+0.5)*255)
			b = (255 - ((w*0.5+0.5)*255))
			c = 255 - (Zmatrix[x][y])
			#c = 255 - Zmatrix[x][y]
			norm.putpixel((x,y), (int(a), int(b), int(c), 255))	

	norm.save("normals/" + picture + "Normal.png")


def smoothXY(XYmatrix, pix, x, y):
	coord = (0,0)
	for u in range(-1,2):
			for v in range(-1,2):
				if(not pix[x, y][3] == 0):
					direction = XYmatrix[x+v][y+u]
					direction = normalize(direction[0], direction[1])
					coord = (coord[0] + direction[0], coord[1] + direction[1])
	XYmatrix[x][y] = normalize(coord[0],coord[1])


def fillXYwithCoords(img):
	matrix = [[0 for y in xrange(img.size[1])] for x in xrange(img.size[0])]
	for y in range(0,img.size[1]):
		for x in range(0,img.size[0]):
			matrix[x][y] = (0,0)
	return matrix

def getXandY(Zmatrix, XYmatrix, img):
	for y in range(3,img.size[1]-3):
		for x in range(3,img.size[0]-3):
			a = getCoord(Zmatrix, x, y)
			XYmatrix[x][y] = a

def getCoord(Zmatrix, x, y):
	coord = (0,0)
	for u in range(-3,4):
		for v in range(-3,4):
			if (Zmatrix[x+v][y+u] < Zmatrix[x][y]):
				direction = normalize(v,u)
				coord = (coord[0] + direction[0], coord[1] + direction[1])
	return normalize(coord[0],coord[1])

def normalize(x,y):
	length = math.sqrt(x*x + y*y)
	#return (x / length, y / length)
	return (float(x) / max(length, 0.1), float(y) / max(length, 0.1))

def getBiggest(Zmatrix, img):
	biggest = 0
	for y in xrange(img.size[1]):
		for x in xrange(img.size[0]):
			if Zmatrix[x][y] > biggest:
				biggest = Zmatrix[x][y]
	return biggest

def findEdges(matrix, pix ,img):
	for y in xrange(img.size[1]):
		for x in xrange(img.size[0]):
			pixel = pix[x, y][3]
			if pixel == 0:
				matrix[x][y] = 0
			else:
				matrix[x][y] = sys.maxint
			if (x == 0 or x == img.size[0]-1 or y == 0 or y == img.size[1]-1) and not pixel == 0:
				matrix[x][y] = 1

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