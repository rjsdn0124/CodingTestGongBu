def new_loc(a,b):
    return a-b if a > b else b-a
def solution(n, m, x, y, r, c, k):
    answer = ''
    dx = new_loc(x,r)
    dy = new_loc(y,c)
    if (k-dx-dy) %2 != 0 or (dx+dy) > k:
        return "impossible"
    
    while k > dx+dy:
        if x < n:
            answer+="d"
            k-=1
            x+=1
        elif y > 1:
            answer+="l"
            k-=1
            y-=1
        else:
            k-=2
            answer+="rl"
        dx = new_loc(x,r)
        dy = new_loc(y,c)  
    answer+="d" * (r-x)
    answer+="l" * (y-c)
    answer+="r" * (c-y)
    answer+="u" * (x-r)
    return answer