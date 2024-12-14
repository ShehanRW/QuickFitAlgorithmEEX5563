import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;

class QuickFit {
    private static ArrayList<LinkedHashMap<String, Integer>> memoryLists = new ArrayList<>();
    private static LinkedHashMap<String, Integer> memoryBlock = new LinkedHashMap<>();
    private static HashMap<String, Integer> process = new HashMap<>();
    private static LinkedHashMap<String, String> allocatedProcess = new LinkedHashMap<>();

    // Initialize the memory blocks and processes
    private static void initializeSystem() {
        memoryBlock.put("Block 1", 8);
        memoryBlock.put("Block 2", 16);
        memoryBlock.put("Block 3", 24);
        memoryBlock.put("Block 4", 32);
        memoryBlock.put("Block 5", 40);
        // Total memory is 120KB

        process.put("P1", 2);
        process.put("P2", 16);
        process.put("P3", 22);
        process.put("P4", 38);
        process.put("P5", 55);
        process.put("P6", 14);
    }

    private static void allocateBlocks() {
        for (String processName : process.keySet()) {
            boolean allocated = false;
            int processSize = process.get(processName);

            for (String blockName : memoryBlock.keySet()) {
                int blockSize = memoryBlock.get(blockName);

                if (processSize == blockSize) {
                    allocatedProcess.put(blockName, processName);
                    memoryBlock.put(blockName, blockSize - processSize);

                    System.out.println("Allocated process " + processName + " to " + blockName);
                    allocated = true;
                    break;
                } else if (processSize + 1 == blockSize) {
                    allocatedProcess.put(blockName, processName);
                    memoryBlock.put(blockName, blockSize - processSize);

                    System.out.println("Allocated process " + processName + " to " + blockName);
                    allocated = true;
                    break;
                } else if (processSize + 2 == blockSize) {
                    allocatedProcess.put(blockName, processName);
                    memoryBlock.put(blockName, blockSize - processSize);

                    System.out.println("Allocated process " + processName + " to " + blockName);
                    allocated = true;
                    break;
                } else if (processSize + 3 == blockSize) {
                    allocatedProcess.put(blockName, processName);
                    memoryBlock.put(blockName, blockSize - processSize);

                    System.out.println("Allocated process " + processName + " to " + blockName);
                    allocated = true;
                    break;
                } else if (processSize + 4 == blockSize) {
                    allocatedProcess.put(blockName, processName);
                    memoryBlock.put(blockName, blockSize - processSize);

                    System.out.println("Allocated process " + processName + " to " + blockName);
                    allocated = true;
                    break;
                } else if (processSize + 5 <= blockSize) {
                    allocatedProcess.put(blockName, processName);
                    memoryBlock.put(blockName, blockSize - processSize);

                    System.out.println("Allocated process " + processName + " to " + blockName);
                    allocated = true;
                    break;
                }
            }
            if (!allocated) {
                System.out.println("Not enough space in memory for process " + processName);
            }
        }
    }

    private static void mergeBlocks() {
        LinkedHashMap<String, Integer> mergedBlocks = new LinkedHashMap<>();
        String previousBlock = null;
        int previousSize = 0;

        for (String blockName : memoryBlock.keySet()) {
            int blockSize = memoryBlock.get(blockName);

            if (previousBlock != null && !allocatedProcess.containsKey(previousBlock)) {
                // Merge if the previous block is free
                previousSize += blockSize;
                mergedBlocks.put(previousBlock, previousSize);
                System.out.println("Merged " + previousBlock + " and " + blockName + " into " + previousBlock);
            } else {
                // No merge, keep current block
                mergedBlocks.put(blockName, blockSize);
                previousBlock = blockName;
                previousSize = blockSize;
            }
        }

        memoryBlock = mergedBlocks; // Update memory block
    }

    private static void deallocateProcess(String processName) {
        String allocatedBlock = null;
        for (String blockName : allocatedProcess.keySet()) {
            if (allocatedProcess.get(blockName).equals(processName)) {
                allocatedBlock = blockName;
                break;
            }
        }

        if (allocatedBlock != null) {
            int processSize = process.get(processName);
            memoryBlock.put(allocatedBlock, memoryBlock.get(allocatedBlock) + processSize);
            allocatedProcess.remove(allocatedBlock);

            System.out.println("Deallocated process " + processName + " from " + allocatedBlock);
        } else {
            System.out.println("Process " + processName + " is not allocated in memory.");
        }
    }

    public static void main(String[] args) {
        initializeSystem();
        allocateBlocks();

        memoryLists.add(memoryBlock);

        System.out.println("\nThe memory after allocation:");
        for (String block : allocatedProcess.keySet()) {
            System.out.println("Memory block " + block + " allocated to process " + allocatedProcess.get(block));
        }

        System.out.println("\nRemaining memory in blocks:");
        for (String block : memoryBlock.keySet()) {
            System.out.println(block + " has " + memoryBlock.get(block) + " KB remaining.");
        }

        // Simulate deallocation
        System.out.println("\nDeallocating processes:");
        deallocateProcess("P2");
        deallocateProcess("P6");

        System.out.println("\nThe memory after deallocation:");
        for (String block : memoryBlock.keySet()) {
            System.out.println(block + " has " + memoryBlock.get(block) + " KB remaining.");
        }

        mergeBlocks();

        System.out.println("\nThe memory after merged:");
        for (String block : memoryBlock.keySet()) {
            System.out.println(block + " has " + memoryBlock.get(block) + " KB remaining.");
        }
    }
}
