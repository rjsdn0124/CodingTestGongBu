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
    timetable.sort()
    bustime = 9 * 60
    j = 0
    l = len(timetable)
    for i in range(n-1):
        count=0
        strtime = getStrBusTime(bustime)
        while m > count and j < l and timetable[j] <= strtime:
            j += 1
            count += 1
        bustime += t
        
    lastBus = getStrBusTime(bustime)
    lastcrew = ''
    while m > 0 and j < l and timetable[j] <= lastBus:
        m -= 1
        if not lastcrew == timetable[j]:
            lastcrew = timetable[j]
        j += 1
        
    answer = lastBus
    if m == 0:
        h,mi = map(int,lastcrew.split(':'))
        answer = getStrBusTime(h*60 + mi - 1)
    return answer