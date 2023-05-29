# import time
# import random

# # Function to calculate the average time
# def calculate_average_time(times):
#     return sum(times) / len(times)

# # Function to adjust the clocks
# def adjust_clocks(times, average_time):
#     adjusted_times = []
#     for t in times:
#         adjusted_time = t + average_time
#         adjusted_times.append(adjusted_time)
#     return adjusted_times

# # Function to simulate clock synchronization using the Berkeley algorithm
# def berkeley_algorithm(times):
#     # Calculate the average time
#     average_time = calculate_average_time(times)

#     # Adjust the clocks
#     adjusted_times = adjust_clocks(times, average_time)

#     # Display the adjusted times
#     print("Adjusted Times:")
#     for i, t in enumerate(adjusted_times):
#         print(f"Clock {i+1}: {t}")

# # Example usage
# if __name__ == '__main__':
#     # Generate random clock times
#     num_clocks = 5
#     clock_times = [random.randint(1, 10) for _ in range(num_clocks)]

#     # Display the initial clock times
#     print("Initial Times:")
#     for i, t in enumerate(clock_times):
#         print(f"Clock {i+1}: {t}")

#     # Simulate clock synchronization using the Berkeley algorithm
#     berkeley_algorithm(clock_times)


 
import time
import random
 
# Function to calculate the average time
def calculate_average_time(times):
    total_time = sum(times)
    average_time = total_time / len(times)
    return average_time
 
# Function to simulate clock drift
def simulate_clock_drift(local_time):
    drift = random.uniform(-0.1, 0.1)  # Random clock drift between -0.1 and 0.1
    local_time += drift
    return local_time
 
# Function to synchronize clocks using the Berkeley algorithm
def berkeley_algorithm(times):
    local_time = time.time()  # Get local time
    local_time = simulate_clock_drift(local_time)  # Simulate clock drift
    average_time = calculate_average_time(times)  # Calculate average time
 
    adjustment = average_time - local_time  # Calculate the adjustment needed
 
    # Adjust the local clock
    local_time += adjustment
 
    return local_time
 
# Main function
def main():
    num_servers = 5
    times = []
 
    # Simulate the clock times of the servers
    for i in range(num_servers):
        server_time = time.time() + random.uniform(-1, 1)  # Random server time with clock drift
        times.append(server_time)
 
    print("Initial server times:", times)
 
    synchronized_time = berkeley_algorithm(times)
 
    print("Synchronized time:", synchronized_time)
 
if __name__ == '__main__':
    main()