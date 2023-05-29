#include <stdio.h>
#include <mpi.h>

int main(int argc, char** argv) {
    int rank, size;
    int N = 100; // Total number of elements
    int n = 4;   // Number of processors
    int localN = N / n; // Number of elements per processor
    int array[N];
    int localArray[localN];
    int localSum = 0;
    int globalSum = 0;

    // Initialize MPI
    MPI_Init(&argc, &argv);
    MPI_Comm_rank(MPI_COMM_WORLD, &rank);
    MPI_Comm_size(MPI_COMM_WORLD, &size);

    // Only the root processor initializes the array
    if (rank == 0) {
        for (int i = 0; i < N; i++) {
            array[i] = i + 1;
        }
    }

    // Distribute the elements of the array to different processors
    MPI_Scatter(array, localN, MPI_INT, localArray, localN, MPI_INT, 0, MPI_COMM_WORLD);

    // Calculate the local sum
    for (int i = 0; i < localN; i++) {
        localSum += localArray[i];
    }

    // Display the intermediate sum calculated at each processor
    printf("Processor %d - Intermediate Sum: %d\n", rank, localSum);

    // Sum up the local sums from each processor
    MPI_Reduce(&localSum, &globalSum, 1, MPI_INT, MPI_SUM, 0, MPI_COMM_WORLD);

    // Only the root processor displays the final sum
    if (rank == 0) {
        printf("Final Sum: %d\n", globalSum);
    }

    // Finalize MPI
    MPI_Finalize();

    return 0;
}
