package net.shadowmage.ancientwarfare.automation.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.shadowmage.ancientwarfare.automation.item.AWAutomationItemLoader;
import net.shadowmage.ancientwarfare.automation.tile.TileWarehouseStorageSmall;

public class BlockWarehouseStorage extends Block
{

public BlockWarehouseStorage(String regName)
  {
  super(Material.rock);
  this.setBlockName(regName);
  this.setCreativeTab(AWAutomationItemLoader.automationTab);
  }

@Override
public boolean hasTileEntity(int metadata)
  {
  return true;
  }

@Override
public TileEntity createTileEntity(World world, int metadata)
  {
  switch(metadata)
  {
  case 0:
  return new TileWarehouseStorageSmall();  
  case 1:
  case 2:
  default:
  return new TileWarehouseStorageSmall();  
  }
  }

}
