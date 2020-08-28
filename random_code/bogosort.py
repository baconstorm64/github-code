import random


def bogoSort(a):
    n = len(a)
    while (is_sorted(a) == False):
        shuffle(a)
        

def shuffle(a):
    n = len(a)
    for i in range(0, n):
            r = random.randint(0,n-1)
            a[i], a[r] = a[r], a[i]


def is_sorted(a):
    sorted_a = sorted(a)
    if sorted_a == a:
        return True
    else:
        return False
    
a = []

bogoSort(a)

print(a)
