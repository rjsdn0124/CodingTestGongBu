from heapq import heappop, heappush

def solution(operations):
    answer = [0,0]
    minheap = []
    maxheap = []
    
    for op in operations:
        com,num_str = op.split(' ')
        num = int(num_str)
        if com == 'I':
            heappush(minheap,num)
            heappush(maxheap,-num)
        elif minheap:
            if num == -1:            
                temp = heappop(minheap)
                maxheap.remove(-temp)
            else:
                temp = heappop(maxheap)
                minheap.remove(-temp)
    if minheap:
        answer[0] = -maxheap[0]
        answer[1] = minheap[0]
    return answer