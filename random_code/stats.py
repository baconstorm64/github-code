"""
Simple Selection sort found on geeksforgeeks.
https://www.geeksforgeeks.org/python-program-for-selection-sort/
"""
def sort(data):
    for i in range(len(data)): 
      
    # Find the minimum element in remaining unsorted array 
        min_idx = i 
        for j in range(i+1, len(data)): 
            if data[min_idx] > data[j]: 
                min_idx = j 
              
    # Swap the found minimum element with the first element         
        data[i], data[min_idx] = data[min_idx], data[i]
    return data

    
def mean(data):
    total = 0
    count = 0
    """
    Sum everything in the array
    and count how many things are in it
    (in retrospect I could have used len()
    but I'm too lazy to change it now)
    """
    for i in data:
        total += i
        count += 1
    print("Mean: " + str(total/count))
    return total/count

def median(data):
    midpoint =len(data)/2
    #Must sort the data to calculate the median
    data = sort(data)
    #If the array is odd, we can just get the middle of it
    if len(data) % 2 == 1:
        print("Median: " + str(data[int(midpoint)]))
    #If the array is even, take the 2 middle points and divide them by 2
    else:
        calc = (data[int(midpoint)] + data[int(midpoint-1)]) / 2
        print("Median: " + str(calc))
    
def mode(data):
    print("Mode still in progress")
    
def main():
    mean([4,3,6,2,8,7])
    median([4,3,6,2,8,7,7,10,3])
    mode([4,4,1,5,10,52,5])
    
main()
