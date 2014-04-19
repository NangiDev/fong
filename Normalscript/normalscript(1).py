import Image
import sys
import math

def main():
	picture = "ufoBlue"
	img = Image.open(picture + ".png")
	norm = Image.new("RGBA", img.size)
	pix = img.load()

	Zmatrix = [[0 for y in xrange(img.size[1])] for x in xrange(img.size[0])]
	XYmatrix = fillXYwithCoords(img)

	findEdges(Zmatrix, pix, img)
	generateZ(Zmatrix, img)
	getXandY(Zmatrix, XYmatrix, img)

	#for y in range(1,img.size[1]-1):
		#for x in range(1,img.size[0]-1):
			#a = smoothXY(XYmatrix, x, y)

	d = 255/getBiggest(Zmatrix, img)

	#for x in xrange(img.size[0]):
		#print(Zmatrix[x])

	for y in xrange(img.size[1]):
		for x in xrange(img.size[0]):
			(q,w) = XYmatrix[x][y]
			a = (255 - ((q*0.5+0.5)*255))
			b = (255 - ((w*0.5+0.5)*255))
			c = 255 - (d*Zmatrix[x][y])
			norm.putpixel((x,y), (int(a), int(b), int(c), 255))	

	norm.save("normals/" + picture + "Normal.png")


def smoothXY(XYmatrix, x, y):
	coord = (0,0)
	for u in range(-1,2):
			for v in range(-1,2):
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

def getXandY(matrix, XYmatrix, img):
	for y in range(1,img.size[1]-1):
		for x in range(1,img.size[0]-1):
			a = getCoord(matrix, x, y)
			XYmatrix[x][y] = a

def getCoord(matrix, x, y):
	coord = (0,0)
	for u in range(-1,2):
		for v in range(-1,2):
			if (matrix[x+v][y+u] < matrix[x][y]):
				direction = normalize(v,u)
				coord = (coord[0] + direction[0], coord[1] + direction[1])
			elif (matrix[x+v][y+u] > matrix[x][y]):
				direction = normalize(v,u)
				coord = (coord[0] - direction[0], coord[1] - direction[1])
	return normalize(coord[0],coord[1])

def normalize(x,y):
	length = math.sqrt(x*x + y*y)
	#return (x / length, y / length)
	return (float(x) / max(length, 0.000001), float(y) / max(length, 0.000001))

def getBiggest(matrix, img):
	biggest = 0
	for y in xrange(img.size[1]):
		for x in xrange(img.size[0]):
			if matrix[x][y] > biggest:
				biggest = matrix[x][y]
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

def getPixel(pix, x, y):
	return pix[x,y][0:3]

def generateZ(matrix, img):
	for y in range(1, img.size[1]-1):
		for x in range(1, img.size[0]-1):
			u = img.size[0]-1 - x
			v = img.size[1]-1 - y
			lowest = getSmallestNeighbour(matrix, u, v)
			if lowest < matrix[u][v]:# and not lowest > 9:
				matrix[u][v] = lowest + 1
			else:
				continue
	for y in range(1, img.size[1]-1):
		for x in range(1, img.size[0]-1):
			lowest = getSmallestNeighbour(matrix, x, y)
			if lowest < matrix[x][y]:# and not lowest > 9:
				matrix[x][y] = lowest + 1
			else:
				continue

def getSmallestNeighbour(matrix, x, y):
	lowest = sys.maxint
	for u in range(-1,2):
		for v in range(-1,2):
			if (matrix[x+v][y+u] < lowest):
				lowest = matrix[x+v][y+u]
	return lowest
main()