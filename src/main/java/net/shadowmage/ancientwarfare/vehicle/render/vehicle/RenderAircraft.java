/**
 * Copyright 2012 John Cummens (aka Shadowmage, Shadowmage4513)
 * This software is distributed under the terms of the GNU General Public License.
 * Please see COPYING for precise license information.
 * <p>
 * This file is part of Ancient Warfare.
 * <p>
 * Ancient Warfare is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * <p>
 * Ancient Warfare is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * <p>
 * You should have received a copy of the GNU General Public License
 * along with Ancient Warfare.  If not, see <http://www.gnu.org/licenses/>.
 */

package net.shadowmage.ancientwarfare.vehicle.render.vehicle;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderManager;
import net.shadowmage.ancientwarfare.vehicle.entity.VehicleBase;
import net.shadowmage.ancientwarfare.vehicle.model.ModelAirplane;


public class RenderAircraft extends RenderVehicleBase {

	ModelAirplane model = new ModelAirplane();

	public RenderAircraft(RenderManager renderManager) {
		super(renderManager);
	}

	@Override
	public void renderVehicle(VehicleBase veh, double x, double y, double z, float yaw, float tick) {
		GlStateManager.rotate(-veh.rotationPitch, 1, 0, 0);
		GlStateManager.rotate(veh.moveHelper.getRotationSpeed() * 10, 0, 0, 1);
		float wheelAngle = veh.wheelRotation + (tick * (veh.wheelRotation - veh.wheelRotationPrev));
		model.setWheelRotations(wheelAngle, wheelAngle, wheelAngle, wheelAngle);
		model.render(veh, 0, 0, 0, 0, 0, 0.0625f);
	}

	@Override
	public void renderVehicleFlag() {
		model.renderFlag();
	}

}
