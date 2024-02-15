def solution(queue1, queue2):
    answer = 0
    s1 = sum(queue1)
    s2 = sum(queue2)
    l = len(queue1)
    m = max(queue1+queue2)
    if (s1+s2)%2!=0 or m > (s1+s2)//2:
        return -1
    now1 = 0
    now2 = 0
    while answer < 3*l+1:
        if s1==s2:
            return answer
        elif s1 > s2:
            now1 %= (2*l)
            if now1 >= l:
                temp = queue2[now1-l]
            else:
                temp = queue1[now1]
            s1 -= temp
            s2 += temp
            now1 += 1
        else:
            now2 %= (2*l)
            if now2 >= l:
                temp = queue1[now2-l]
            else:
                temp = queue2[now2]
            s2 -= temp
            s1 += temp
            now2 += 1
        answer+=1
    return -1