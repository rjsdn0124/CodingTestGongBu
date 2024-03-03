def solution(s):
    l = len(s)
    answer = l
    
    for i in range(1,l//2+1):
        zipped_str = ''
        temp = ''
        cnt = 0
        for j in range(0,l,i):
            cnt += 1
            if temp != s[j:j+i]:
                zipped_str += str(cnt) + temp if cnt > 1 else temp
                cnt = 0
                temp = s[j:j+i]
        zipped_str += str(cnt) + temp if cnt >= 1 else temp
        answer = min(answer, len(zipped_str))
           
    return answer