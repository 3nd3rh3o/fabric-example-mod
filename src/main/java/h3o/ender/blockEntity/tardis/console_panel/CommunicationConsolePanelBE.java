package h3o.ender.blockEntity.tardis.console_panel;

import h3o.ender.blockEntity.RegisterBlockEntities;
import h3o.ender.blockEntity.tardis.TardisBentDependant;
import h3o.ender.blocks.tardis.console_panel.CommunicationConsolePanel;
import h3o.ender.entities.Tardis;
import h3o.ender.structures.tardis.DimensionalStorageHelper;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtElement;
import net.minecraft.nbt.NbtList;
import net.minecraft.network.packet.s2c.play.BlockEntityUpdateS2CPacket;
import net.minecraft.util.math.BlockPos;
import software.bernie.geckolib.animatable.GeoBlockEntity;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animation.AnimationController;
import software.bernie.geckolib.core.animation.RawAnimation;
import software.bernie.geckolib.core.animation.AnimatableManager.ControllerRegistrar;
import software.bernie.geckolib.core.object.PlayState;
import software.bernie.geckolib.util.GeckoLibUtil;

public class CommunicationConsolePanelBE extends BlockEntity implements GeoBlockEntity, TardisBentDependant {
    private final AnimatableInstanceCache geoCache = GeckoLibUtil.createInstanceCache(this);
    private NbtCompound tardisCircuits;
    private Tardis trds;

    public CommunicationConsolePanelBE(BlockPos pos, BlockState state) {
        super(RegisterBlockEntities.COMMUNICATION_CONSOLE_PANEL_BE, pos, state);
    }

    @Override
    public void registerControllers(ControllerRegistrar controllers) {
        controllers.add(new AnimationController<>(this, (animationState) -> {
            if (!this.world.getBlockState(pos).getBlock().equals(Blocks.AIR) && this.world.getBlockState(pos).get(CommunicationConsolePanel.OPENNED)) {
                if (animationState.getController().getCurrentAnimation() == null || !animationState.getController()
                        .getCurrentAnimation().animation().name().equals("open_panel")) {
                    return animationState.setAndContinue(RawAnimation.begin().thenPlayAndHold("open_panel"));
                }
            } else {
                if (animationState.getController().getCurrentAnimation() == null || !animationState.getController()
                        .getCurrentAnimation().animation().name().equals("close_panel")) {
                    return animationState.setAndContinue(RawAnimation.begin().thenPlayAndHold("close_panel"));
                }
            }
            return PlayState.CONTINUE;
        }));
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return geoCache;
    }

    @Override
    public NbtCompound toInitialChunkDataNbt() {
        return createNbt();
    }

    @Override
    public BlockEntityUpdateS2CPacket toUpdatePacket() {
        return BlockEntityUpdateS2CPacket.create(this);
    }

    @Override
    public void readNbt(NbtCompound nbt) {
        super.readNbt(nbt);
        this.tardisCircuits = nbt.getCompound("Circuits");
    }

    @Override
    protected void writeNbt(NbtCompound nbt) {
        Tardis tardis = DimensionalStorageHelper.getTardis(world.getServer(), pos);
        if (tardis != null) {
            nbt.put("Circuits", tardis.getDataTracker().get(Tardis.CIRCUITS));
        }
    }

    public NbtList getTardisCircuits() {
        if (tardisCircuits == null) {
            return null;
        }
        return tardisCircuits.getList("Circuits", NbtElement.LIST_TYPE);
    }

    @Override
    public void register() {
        this.trds = DimensionalStorageHelper.getTardis(world.getServer(), pos);
        if (trds != null) {
            trds.registerBlock(this);
        }
    }

    @Override
    public void unRegister() {
        if (trds != null) {
            trds.unRegisterBlock(this);
        }
    }
}
