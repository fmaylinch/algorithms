

def water_area(heights):

    maxes = [[] for i in range(len(heights))]

    # detect max to the left
    max_last = 0
    for i in range(len(heights)):
        max_last = max(max_last, heights[i])
        maxes[i].append(max_last)

    # detect max to the right
    max_last = 0
    for i in reversed(range(len(heights))):
        max_last = max(max_last, heights[i])
        maxes[i].append(max_last)

    #  Now maxes[i] contains [maxLeft, MaxRight] for every index
    total = 0
    for i in range(len(heights)):
        total += min(maxes[i]) - heights[i]

    return total


print(water_area([0, 8, 0, 0, 5, 0, 0, 10, 0, 0, 1, 1, 0, 3]))
