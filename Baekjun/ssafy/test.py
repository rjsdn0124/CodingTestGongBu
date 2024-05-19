from collections import deque

def isBest(num_board):
	prev = num_board[0]
	for c in num_board[1:]:
		if prev < c:
			return False
		prev = c
	return True

def whenSameIn(num_board):
	dic = {}
	FLAG = False
	for c in num_board:
		if not c in dic:
			dic[c] = 0
		dic[c] += 1
	for k in dic:
		if dic[k] >= 2:
			FLAG = True
	if not FLAG:
		temp = list(num_board)
		temp[l-2],temp[l-1] = temp[l-1],temp[l-2]
		num_board = ''.join(temp)
	return num_board

def bfs(l,count,num_board):
	q = deque()
	q.append([count,num_board])
	result = ''
	while q:
		c,nb = q.popleft()
		c -= 1
		for i in range(l):
			for j in range(i+1,l):
				if not nb[i] == nb[j]:
					temp = list(nb)
					temp[i],temp[j] = temp[j],temp[i]
					new_temp = ''.join(temp)

					if isBest(new_temp):
						if c % 2 == 0:
							return new_temp
						else:
							return whenSameIn(new_temp)
					else:
						if c == 0:
							result = max(result,new_temp)
						else:
							q.append([c,new_temp])
	return result

T = int(input())
for test_case in range(1, T + 1):
	num_board, change_count_string = input().split()
	change_count = int(change_count_string)
	l = len(num_board)
	result = bfs(l,change_count,num_board)
	print(f'#{test_case}', result)