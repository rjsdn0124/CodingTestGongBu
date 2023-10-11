import sys
given_line=sys.stdin.readline().strip()
search_keyword=list(sys.stdin.readline().strip())
leng=len(search_keyword)
arr=[]
for char in given_line:
    arr.append(char)
    if char == search_keyword[-1]:
        if arr[-leng:] == list(search_keyword):
            for i in range(leng):
                arr.pop()
if arr:
    print(*arr, sep='')
else:
    print("FRULA")