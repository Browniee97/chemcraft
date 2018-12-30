package browniee.chemcraft.OrganicDecomposer;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;

public class containerOrganicDecomposer extends Container {

    private tileOrganicDecomposer te;

    public containerOrganicDecomposer(IInventory playerInventory, tileOrganicDecomposer te) {
        this.te = te;

        addOwnSlots();
        addPlayerSlots(playerInventory);
    }

    private void addPlayerSlots(IInventory playerInventory) {
        //Slots for the main inventory
        for (int row = 0; row < 3; ++row) {
            for (int col = 0; col < 9; ++col) {
                int x = 10 + col * 18;
                int y = row * 18 + 70;
                this.addSlotToContainer(new Slot(playerInventory, col + row * 9 + 10, x, y));
            }
        }

        //Hotbar slots
        for (int col = 0; col < 9; ++col) {
            int x = 10 + col * 18;
            int y = 58 + 70;
            this.addSlotToContainer(new Slot(playerInventory, col, x, y));
        }
    }

    private void addOwnSlots() {
        IItemHandler itemHandler = this.te.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null);
        int x1 = 17;
        int x2 = 111;
        int y = 6;

        //Adding our own slots
        int slotIndex = 0;
        for (int i = 0; i < 3; i++) {
            for(int j = 0; j < 3; j++){
                addSlotToContainer(new SlotItemHandler(itemHandler, slotIndex, x1 + i*18, y + j*18));
                slotIndex++;
                addSlotToContainer(new SlotItemHandler(itemHandler, slotIndex, x2 + i*18, y + j*18));
                slotIndex++;
            }
        }


    }

    @Override
    public ItemStack transferStackInSlot(EntityPlayer playerIn, int index) {
        ItemStack itemStack = ItemStack.EMPTY;
        Slot slot = this.inventorySlots.get(index);

        if (slot != null && slot.getHasStack()) {
            ItemStack itemStack1 = slot.getStack();
            itemStack = itemStack1.copy();

            if (index < tileOrganicDecomposer.SIZE) {
                if (!this.mergeItemStack(itemStack1, tileOrganicDecomposer.SIZE, this.inventorySlots.size(), true)) {
                    return ItemStack.EMPTY;
                }
            } else if (!this.mergeItemStack(itemStack1, 0, tileOrganicDecomposer.SIZE, false)) {
                return ItemStack.EMPTY;
            }

            if (itemStack1.isEmpty()) {
                slot.putStack(ItemStack.EMPTY);
            } else {
                slot.onSlotChanged();
            }
        }
        return itemStack;
    }

    @Override
    public boolean canInteractWith(EntityPlayer entityPlayer) {
        return te.canInteractWtih(entityPlayer);
    }
}
