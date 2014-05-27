package net.shadowmage.ancientwarfare.npc.ai;

import net.minecraft.entity.Entity;
import net.minecraft.pathfinding.PathEntity;
import net.shadowmage.ancientwarfare.npc.config.AWNPCStatics;
import net.shadowmage.ancientwarfare.npc.entity.NpcBase;

public class NpcAIAttackMelee2 extends NpcAI
{

Entity target;
PathEntity path;
double moveSpeed = 1.d;


int moveRetryDelay = 0;
int attackDelay = 0;

public NpcAIAttackMelee2(NpcBase npc)
  {
  super(npc);
  }

@Override
public boolean shouldExecute()
  {
//  Entity t = npc.getAttackTarget();
//  if(t==null || t.isDead){return false;}
  return npc.getAttackTarget()!=null && !npc.getAttackTarget().isDead;
  }

@Override
public boolean continueExecuting()
  {  
  return npc.getAttackTarget()!=null && !npc.getAttackTarget().isDead;
  }

@Override
public void startExecuting()
  {
  npc.addAITask(TASK_ATTACK);
  target = npc.getAttackTarget();
  attackDelay = 0;
  moveRetryDelay = 0;
  }

@Override
public void updateTask()
  {
  npc.getLookHelper().setLookPositionWithEntity(target, 30.f, 30.f);
  double distanceToEntity = this.npc.getDistanceSq(target.posX, target.boundingBox.minY, target.posZ);
  double attackDistance = (double)(this.npc.width * 2.0F * this.npc.width * 2.0F + target.width);
  if(distanceToEntity>attackDistance)
    {
    moveToTarget(distanceToEntity);
    }
  else
    {
    attackTarget();
    }
  }

private void moveToTarget(double distance)
  {
  npc.addAITask(TASK_MOVE);
  if(moveRetryDelay>0)
    {
    moveRetryDelay--;    
    }
  if(moveRetryDelay<=0)
    {
    npc.getNavigator().tryMoveToEntityLiving(target, moveSpeed);
    moveRetryDelay=10;//base .5 second retry delay
    if(distance>256){moveRetryDelay+=10;}//add .5 seconds if distance>16
    if(distance>1024){moveRetryDelay+=20;}//add another 1 second if distance>32
    }
  }

private void attackTarget()
  {
  npc.removeAITask(TASK_MOVE);
  if(attackDelay>0){attackDelay--;}
  if(attackDelay<=0)
    {
    npc.swingItem();
    npc.attackEntityAsMob(target);    
    this.attackDelay=20;//TODO set attack delay from npc-attributes? 
    int xp = target.isDead ? AWNPCStatics.npcXpFromKill : AWNPCStatics.npcXpFromAttack;
    npc.addExperience(xp);    
    }
  }

@Override
public void resetTask()
  {
  super.resetTask();
  npc.removeAITask(TASK_MOVE + TASK_WORK);
  target = null;
  attackDelay = 0;
  moveRetryDelay = 0;
  }

}
