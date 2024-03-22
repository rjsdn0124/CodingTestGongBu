def getStrBusTime(t):
    h = t // 60
    m = t % 60
    strtime = ''
    if h < 10:
        strtime += '0'
    strtime += str(h) + ':'
    if m < 10:
        strtime += '0'
    strtime += str(m)
    return strtime
    
def solution(n, t, m, timetable):
    answer = ''
    
    timetable.sort()
    
    bustime = 9 * 60
    j = 0
    l = len(timetable)
    count = 0
    
    for i in range(n):
        count=0
        answer = getStrBusTime(bustime)
        while m > count and j < l and timetable[j] <= answer:
            j += 1
            count += 1
        bustime += t
        
    if m == count:
        h,mi = map(int,timetable[j - 1].split(':'))
        answer = getStrBusTime(h * 60 + mi - 1)
        
    return answer