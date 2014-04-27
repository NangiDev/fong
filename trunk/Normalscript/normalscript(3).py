import Image		#Image-processing libiary. Python Imaging Library (PIL)
import sys			#Standard System library
import math			#Standard Math library

def main():
	picture = "cockpitGreen_0"						#name of the picture without file extension
	img = Image.open(picture + ".png")		#open requested image with file extension. Change ".png" for other file type
	norm = Image.new("RGBA", img.size)		#Creates a new image that will be loaded with RGBA from normals
	pix = img.load()						#Loads the requested image into an list for faster access to pixels

	Zmatrix = [[0.0 for y in xrange(img.size[1])] for x in xrange(img.size[0])]		#Creates an matrix by the same dimensions as the requseted image. Will be loaded with the Z-coordinates

	findEdges(Zmatrix, pix, img)			#determine which pixels are transparent and not. Transparent pixels will get Z-value = 0 and non-transparent will get Z-value = maxInteger.
	generateZ(Zmatrix, img)					#Generate the Z-values by looking att the lowest neighbour and set value to lowest neighbour+1.			

	d = getBiggest(Zmatrix, img)

	#for y in xrange(img.size[1]):
		#for x in xrange(img.size[0]):
			#Zmatrix[x][y] = Zmatrix[x][y] / d

	#for x in xrange(img.size[0]):		#Uncomment these 1wo rows to print the matrix containing the Z-values.
		#print(Zmatrix[x])	

	for y in range(1, img.size[1]-1):			#Loop through the picture.
		for x in range(1,img.size[0]-1):
			(r,g,b) = generateNormal(Zmatrix, x, y)
			r = (r*0.5+0.5)*255
			g = 255 - (g*0.5+0.5)*255
			b = (b*0.5+0.5)*255

			r = r + 0.5
			g = g + 0.5
			b = b + 0.5

			norm.putpixel((x,y), (int(r), int(g), int(b), 255))	#put each pixel in the new image we created in the beginning

	norm.save("normals/" + picture + "Normal.png")		#save the normalmap as .png in folder normals


def generateNormal(Zmatrix, x, y):

	#[A][B][C]
	#[D][E][F]
	#[G][H][I]

	B = (x,y-1,Zmatrix[x][y-1])
	D = (x-1,y,Zmatrix[x-1][y])
	F = (x+1,y,Zmatrix[x+1][y])
	H = (x,y+1,Zmatrix[x][y+1])

	HB = normalize(subVector(H,B))
	DF = normalize(subVector(D,F))

	N1 = normalize(cross(HB,DF))

	return normalize(N1)

def addVector(p1,p2):
	return (float(p1[0])+float(p2[0]), float(p1[1])+float(p2[1]), float(p1[2])+float(p2[2]))

def subVector(p1, p2):
	return (float(p1[0])-float(p2[0]), float(p1[1])-float(p2[1]), float(p1[2])-float(p2[2]))

def getBiggest(Zmatrix, img):
	biggest = 0.0
	for y in xrange(img.size[1]):
		for x in xrange(img.size[0]):
			if Zmatrix[x][y] > biggest:
				biggest = Zmatrix[x][y]
	return biggest

def normalize(vector):							#Returns an normalized vector.
	x,y,z = vector[0],vector[1],vector[2]
	length = math.sqrt(x*x + y*y + z*z)			#Calculates the lenght by add the x- and y-values that is powered in two. Then squareroot the result.
	return (float(x) / max(length, 0.0001), float(y) / max(length, 0.0001), float(z) / max(length, 0.0001))		#Normalize by dividing the vector by the lenght. max() is to avoid devision by zero.

def cross(a, b):
    c = [a[1] *b[2] - a[2]*b[1],
         a[2]*b[0] - a[0]*b[2],
         a[0]*b[1] - a[1]*b[0]]

    return c

def findEdges(Zmatrix, pix ,img):		#determine which pixels are transparent and not. Transparent pixels will get Z-value = 0 and non-transparent will get Z-value = maxInteger.
	for y in xrange(img.size[1]):		#Looping through image
		for x in xrange(img.size[0]):
			pixel = pix[x, y][3]		#Request the alpha value for specific pixel
			if pixel == 0:
				Zmatrix[x][y] = 0.0				#If transparent set Z-value to 0
			else:
				Zmatrix[x][y] = 99999.0		#If transparent set Z-value to max integer
			if (x == 0 or x == img.size[0]-1 or y == 0 or y == img.size[1]-1) and not pixel == 0:
				Zmatrix[x][y] = 1.0				#To avoid out of range later on, set all the non-transparent pixels by the edge of the image with 1.

def generateZ(Zmatrix, img):					#Generate the Z-values by looking att the lowest neighbour and set value to lowest neighbour+1.
	for y in range(1, img.size[1]-1):			#Looping through image
		for x in range(1, img.size[0]-1):
			u = img.size[0]-1 - x				#Starts from the right-down-corner
			v = img.size[1]-1 - y
			lowest = getSmallestNeighbour(Zmatrix, u, v)	#Gets the smallest neightbour
			if lowest < Zmatrix[u][v]:						#Check if the smallest neightbour is smaller than current Z-value
				Zmatrix[u][v] = lowest + 1.0			#if it is then set current z-value to smallest neighbour+1
			else:
				continue									#Else just continue
	for y in range(1, img.size[1]-1):
		for x in range(1, img.size[0]-1):		#Do the same all over again, but start from top-left-corner
			lowest = getSmallestNeighbour(Zmatrix, x, y)
			if lowest < Zmatrix[x][y]:
				Zmatrix[x][y] = lowest + 1.0
			else:
				continue

def getSmallestNeighbour(Zmatrix, x, y):		#Return the smallest neightbour
	lowest = 99999.0							#Set lowest to max integer.
	for u in range(-1,2):
		for v in range(-1,2):					#Loop through the neighbours
			if (Zmatrix[x+v][y+u] < lowest):	#If the found value is lower than the previous, replace it.
				lowest = Zmatrix[x+v][y+u]		#Return ether max integer or a lower neightbour
	return lowest

main()		#Starts the main program