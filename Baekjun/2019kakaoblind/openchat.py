def solution(record):
    answer = []
    tup = {}
    save_point = []
    
    for command in record:
        a = command.split(" ")
        if a[0] == 'Enter':
            save_point.append(f'{a[1]} e')
            tup[a[1]] = a[2]
        elif a[0] == 'Leave':
            save_point.append(f'{a[1]} l')
        elif a[0] == 'Change':
            tup[a[1]] = a[2]
            
    for i in range(len(save_point)):
        ta = save_point[i].split(' ')
        msg = '들어왔습니다.' if ta[1] == 'e' else '나갔습니다.'
        answer.append(f'{tup[ta[0]]}님이 {msg}')
    return answer