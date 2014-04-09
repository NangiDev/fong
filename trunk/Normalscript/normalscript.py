import Image
import sys
im = Image.open("ufoBlue.png")
pix = im.load()
#print im.size
#print pix[0,0]
res = [[0 for x in xrange(im.size[0])] for x in xrange(im.size[1])]
for i in range(im.size[0]):
    for j in range(im.size[1]):
        if pix[i, j][3] == 0:
            res[i][j] = 0
        else:
            res[i][j] = 1000000000
        #sys.stdout.write(str(res[i][j]))
    #print '\n'

#print "finnished"

def adjacent_passages(mat, pos):
    possible = []

def bfs(mat, nod):
    #print(nod)
    #visited.append(nod[0])
    
    if mat[nod[0][0]][nod[0][1]] == 0:
        return nod
    
    adjacent = adjacent_passages2(mat, nod[0])
    if adjacent == []:
        return False
    print(adjacent)
    for i in range(0, len(adjacent), 1):
        nod.insert(0, adjacent[i])
   
    solution = bfs(mat, nod)

    if(solution != False):
        return solution
    nod.pop(0)
    
    return bfs(mat, nod)
