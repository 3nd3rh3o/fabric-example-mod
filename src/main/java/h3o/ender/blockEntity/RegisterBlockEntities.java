package h3o.ender.blockEntity;

import h3o.ender.DwMod;
import h3o.ender.blockEntity.tardis.RotorBaseBE;
import h3o.ender.blockEntity.tardis.TerminalBE;
import h3o.ender.blockEntity.tardis.console_panel.CommunicationConsolePanelBE;
import h3o.ender.blockEntity.tardis.console_panel.FabricationConsolePanelBE;
import h3o.ender.blockEntity.tardis.engine.TardisMaintenanceEngineUpperBE;
import h3o.ender.blocks.RegisterBlocks;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class RegisterBlockEntities {
        public static final BlockEntityType<RotorBaseBE> ROTOR_BASE_BE = Registry.register(Registries.BLOCK_ENTITY_TYPE,
                        new Identifier(DwMod.MODID, "tardis.console.rotor_base"),
                        FabricBlockEntityTypeBuilder.create(RotorBaseBE::new, RegisterBlocks.ROTOR_BASE).build());
        public static final BlockEntityType<TerminalBE> TERMINAL_BE = Registry.register(Registries.BLOCK_ENTITY_TYPE,
                        new Identifier(DwMod.MODID, "tardis.console.terminal"),
                        FabricBlockEntityTypeBuilder.create(TerminalBE::new, RegisterBlocks.TERMINAL).build());
        public static final BlockEntityType<FabricationConsolePanelBE> FABRICATION_CONSOLE_PANEL_BE = Registry
                        .register(Registries.BLOCK_ENTITY_TYPE,
                                        new Identifier(DwMod.MODID, "tardis.console_panel.fabrication"),
                                        FabricBlockEntityTypeBuilder
                                                        .create(FabricationConsolePanelBE::new,
                                                                        RegisterBlocks.FABRICATION_CONSOLE_PANEL)
                                                        .build());
        public static final BlockEntityType<CommunicationConsolePanelBE> COMMUNICATION_CONSOLE_PANEL_BE = Registry
                        .register(Registries.BLOCK_ENTITY_TYPE,
                                        new Identifier(DwMod.MODID, "tardis.console_panel.communication"),
                                        FabricBlockEntityTypeBuilder
                                                        .create(CommunicationConsolePanelBE::new,
                                                                        RegisterBlocks.COMMUNICATION_CONSOLE_PANEL)
                                                        .build());
        public static final BlockEntityType<TardisMaintenanceEngineUpperBE> TARDIS_MAINTENANCE_ENGINE_UPPER_BE = Registry.register(Registries.BLOCK_ENTITY_TYPE,
                        new Identifier(DwMod.MODID, "tardis.maintenance.engine.upper"),
                        FabricBlockEntityTypeBuilder.create(TardisMaintenanceEngineUpperBE::new, RegisterBlocks.TARDIS_MAINTENANCE_ENGINE_UPPER).build());
}
