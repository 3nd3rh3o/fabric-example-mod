package h3o.ender.items;

import h3o.ender.blocks.RegisterBlocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.state.property.Properties;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ProtyonUnit extends Item {

    public ProtyonUnit(Settings settings) {
        super(settings);
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        if (!context.getWorld().isClient() && context.getWorld().getBlockState(context.getBlockPos()).getBlock()
                .equals(RegisterBlocks.GRAY_PRINT)) {
            BlockPos pos = context.getBlockPos();
            World world = context.getWorld();
            world.setBlockState(pos, RegisterBlocks.GROWING_TARDIS.getDefaultState().with(Properties.HORIZONTAL_FACING,
                    world.getBlockState(pos).get(Properties.HORIZONTAL_FACING)), 3);
            ItemStack stack = context.getPlayer().getStackInHand(context.getHand());
            stack.setCount(stack.getCount() - 1);
            context.getPlayer().setStackInHand(context.getHand(), stack);
            return ActionResult.CONSUME;
        }
        return ActionResult.PASS;
    }

}
