import Image		#Image-processing libiary. Python Imaging Library (PIL)
import sys			#Standard System library
import math			#Standard Math library

def main():
	picture = "ufoBlue"						#name of the picture without file extension
	img = Image.open(picture + ".png")		#open requested image with file extension. Change ".png" for other file type
	norm = Image.new("RGBA", img.size)		#Creates a new image that will be loaded with RGBA from normals
	pix = img.load()						#Loads the requested image into an list for faster access to pixels

	Zmatrix = [[0 for y in xrange(img.size[1])] for x in xrange(img.size[0])]		#Creates an matrix by the same dimensions as the requseted image. Will be loaded with the Z-coordinates
	XYmatrix = fillXYwithCoords(img)												#fillXYwithCoords does the same as the row above, but instead of integer it fills it with tuples (0,0)

	findEdges(Zmatrix, pix, img)			#determine which pixels are transparent and not. Transparent pixels will get Z-value = 0 and non-transparent will get Z-value = maxInteger.
	generateZ(Zmatrix, img)					#Generate the Z-values by looking att the lowest neighbour and set value to lowest neighbour+1.
	getXandY(Zmatrix, XYmatrix, img)		#Generate the X- and Y-values by calculate the direction to each lowest neighbour and sum it up and normalize it.

	d = 255/getBiggest(Zmatrix, img)

	for x in xrange(img.size[0]):		#Uncomment these two rows to print the matrix containing the Z-values.
		print(Zmatrix[x])				

	#for x in xrange(img.size[0]):		#Uncomment these two rows to print the matrix containing the XY-values.
		#print(XYmatrix[x])

	for y in xrange(img.size[1]):			#Loop through the picture.
		for x in xrange(img.size[0]):
			(q,w) = XYmatrix[x][y]			#get the X- and Y-coords for specific pixel
			r = (255 - (q*0.5+0.5)*255)
			g = (w*0.5+0.5)*255
			b = 255 - (Zmatrix[x][y])
			norm.putpixel((x,y), (int(r), int(g), int(b), 255))	#put each pixel in the new image we created in the beginning

	norm.save("normals/" + picture + "Normal.png")		#save the normalmap as .png in folder normals

def getBiggest(Zmatrix, img):
	biggest = 0
	for y in xrange(img.size[1]):
		for x in xrange(img.size[0]):
			if Zmatrix[x][y] > biggest:
				biggest = Zmatrix[x][y]
	return biggest

def fillXYwithCoords(img):		#returns an matrix by the same dimensions as the requseted image and fills it with tuples (0,0)
	matrix = [[0 for y in xrange(img.size[1])] for x in xrange(img.size[0])]
	for y in range(0,img.size[1]):
		for x in range(0,img.size[0]):
			matrix[x][y] = (0,0)
	return matrix

def findEdges(Zmatrix, pix ,img):		#determine which pixels are transparent and not. Transparent pixels will get Z-value = 0 and non-transparent will get Z-value = maxInteger.
	for y in xrange(img.size[1]):		#Looping through image
		for x in xrange(img.size[0]):
			pixel = pix[x, y][3]		#Request the alpha value for specific pixel
			if pixel == 0:
				Zmatrix[x][y] = 0				#If transparent set Z-value to 0
			else:
				Zmatrix[x][y] = sys.maxint		#If transparent set Z-value to max integer
			if (x == 0 or x == img.size[0]-1 or y == 0 or y == img.size[1]-1) and not pixel == 0:
				Zmatrix[x][y] = 1				#To avoid out of range later on, set all the non-transparent pixels by the edge of the image with 1.

def generateZ(Zmatrix, img):					#Generate the Z-values by looking att the lowest neighbour and set value to lowest neighbour+1.
	for y in range(1, img.size[1]-1):			#Looping through image
		for x in range(1, img.size[0]-1):
			u = img.size[0]-1 - x				#Starts from the right-down-corner
			v = img.size[1]-1 - y
			lowest = getSmallestNeighbour(Zmatrix, u, v)	#Gets the smallest neightbour
			if lowest < Zmatrix[u][v]:						#Check if the smallest neightbour is smaller than current Z-value
				Zmatrix[u][v] = (lowest + 1)*1.5			#if it is then set current z-value to smallest neighbour+1
			else:
				continue									#Else just continue
	for y in range(1, img.size[1]-1):
		for x in range(1, img.size[0]-1):		#Do the same all over again, but start from top-left-corner
			lowest = getSmallestNeighbour(Zmatrix, x, y)
			if lowest < Zmatrix[x][y]:
				Zmatrix[x][y] = (lowest + 1)*1.5
			else:
				continue

def getSmallestNeighbour(Zmatrix, x, y):		#Return the smallest neightbour
	lowest = sys.maxint							#Set lowest to max integer.
	for u in range(-1,2):
		for v in range(-1,2):					#Loop through the neighbours
			if (Zmatrix[x+v][y+u] < lowest):	#If the found value is lower than the previous, replace it.
				lowest = Zmatrix[x+v][y+u]		#Return ether max integer or a lower neightbour
	return lowest

def getXandY(Zmatrix, XYmatrix, img):			#Generate the X- and Y-values by calculate the direction to each lowest neighbour and sum it up and normalize it.
	for y in range(1,img.size[1]-1):			#Loop through image, but skipping the edges to avoid out of range later on.
		for x in range(1,img.size[0]-1):		
			a = getCoord(Zmatrix, x, y)			#Get the normalized X- and Y-coordinate
			XYmatrix[x][y] = a					#Set XY-value in the matrix

def getCoord(Zmatrix, x, y):					#Returns the normalized X- and Y-coordinate
	coord = (0,0)
	for u in range(-1,2):						#Loop through the neighbours
		for v in range(-1,2):
			if (Zmatrix[x+v][y+u] < Zmatrix[x][y]):		#If neightbour is smaller, calculate its direction, normalize it and add it to the allready found coords.
				direction = normalize(-v,-u)
				coord = (coord[0] + direction[0], coord[1] + direction[1])
	return normalize(coord[0],coord[1])					#Normalize the end result and return it.

def normalize(x,y):							#Returns an normalized vector.
	length = math.sqrt(x*x + y*y)			#Calculates the lenght by add the x- and y-values that is powered in two. Then squareroot the result.
	return (float(x) / max(length, 0.1), float(y) / max(length, 0.1))		#Normalize by dividing the vector by the lenght. max() is to avoid devision by zero.

main()		#Starts the main program