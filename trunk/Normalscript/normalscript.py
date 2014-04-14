import Image
import sys
im = Image.open("ufoBlue.png")
pix = im.load()
visited = []
#print im.size
#print pix[0,0]
res = [[0 for x in xrange(im.size[0])] for x in xrange(im.size[1])]
for i in range(im.size[0]):
    for j in range(im.size[1]):
        if pix[i, j][3] == 0:
            res[i][j] = 0
        else:
            res[i][j] = 1
        sys.stdout.write(str(res[i][j]))
    print ''






def adjacent_passages(mat, pos):
    possible = []

    if pos[0] > 0 and pos[0] <= len(mat) and pos[1] >= 0:
        if searchvisited(mat, pos[0]-1, pos[1]) == False:
            possible += [(pos[0]-1, pos[1])]
    if pos[0] >= 0 and pos[0] < len(mat)-1 and pos[1] >= 0:
    	if searchvisited(mat, pos[0]+1, pos[1]) == False:
            possible += [(pos[0]+1, pos[1])]
    if pos[1] > 0 and pos[1] <= len(mat[0]) and pos[0] >= 0: 
        if searchvisited(mat, pos[0], pos[1]-1) == False:
            possible += [(pos[0], pos[1]-1)]
    if pos[1] >= 0 and pos[1] < len(mat[0])-1 and pos[0] >= 0:
        if searchvisited(mat, pos[0], pos[1]+1) == False:
            possible += [(pos[0], pos[1]+1)]

    return possible

def bfs(mat, nod):
    #print(nod)
    visited.append(nod[0])
    
    if mat[nod[0][0]][nod[0][1]] == 0:
        return nod
    
    adjacent = adjacent_passages(mat, nod[0])
    if adjacent == []:
        return False

    #print(adjacent)
    for i in range(0, len(adjacent), 1):
        nod.insert(0, adjacent[i])
   
    solution = bfs(mat, nod)

    if(solution != False):
        return solution
    nod.pop(0)
    
    return bfs(mat, nod)

def searchvisited(mat, x, y):
    pos = (x,y)
    
    for i in range(0, len(visited), 1):
        if pos == visited[i]:
            return True
    return False


for i in range(im.size[0]):
	for j in range(im.size[1]):
		if res[i][j] != 0:
			res[i][j] = len(bfs(res, (res[i][j])))
		sys.stdout.write(str(res[i][j]))
	print ''


#start = [(30, 30)]
#pathToAlpha = bfs(res, start)
#print pathToAlpha