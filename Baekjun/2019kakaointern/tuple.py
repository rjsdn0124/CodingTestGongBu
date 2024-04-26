def solution(s):
    answer = []
    def remove_goalho_and_split(gs):
        return gs[2:-2].split('},{')
    set_arr = remove_goalho_and_split(s)
    set_arr.sort(key= lambda x:len(x))
    sum_set = 0
    for sa in set_arr:
        a = list(map(int,sa.split(',')))
        ts = sum(a)
        answer.append(ts - sum_set)
        sum_set = ts

    return answer