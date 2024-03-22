from collections import deque

def solution(begin, target, words):
    answer = 0
    if not target in words:
        return answer
    else:
        q = deque()
        q.append([begin,0,words[:]])
        dic={}
        l = len(begin)
        while answer == 0 and q:
            nw,c,warr = q.popleft()
            for wi in range(len(warr)):
                diffc = 0
                for i in range(l):
                    if not warr[wi][i] == nw[i]:
                        diffc += 1
                if diffc == 1:
                    if warr[wi] == target:
                        return c+1
                    if not warr[wi] in dic:
                        dic[warr[wi]] = c+1
                        q.append([warr[wi],c+1,warr[:wi]+warr[wi+1:]])
    return answer