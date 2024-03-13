def solution(new_id):
    answer = ''
    new_id = new_id.lower()
    isFirst = True
    i = 0
    
    temp = ''
    for c in new_id:
        if 'a' <= c <='z' or '0' <= c <= '9' or c == '-' or c =='_' or c == '.':
            temp += (c)
            
    while i < len(temp):
        c = temp[i]
        if c == '.':
            answer += c
            while i < len(temp) and temp[i] == '.':
                i += 1
        else:
            answer += c
            i+=1
            
    if len(answer) > 0:
        if answer[0] == '.':
            answer = answer[1:] if len(answer) > 1 else answer
        answer = answer[:15] if len(answer) > 15 else answer
        if answer[-1] == '.':
            answer = answer[:-1]
    
    if len(answer) == 0:
        answer = 'aaa'
    while len(answer) < 3:
        answer += answer[-1]
    return answer