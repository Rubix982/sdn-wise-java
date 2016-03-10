/*
 * Copyright (C) 2015 SDN-WISE
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.github.sdnwiselab.sdnwise.flowtable;

import java.util.Arrays;

/**
 * AbstractAction is part of the structure of the Entry of a FlowTable. This
 * Class implements FlowTableInterface.
 *
 * @author Sebastiano Milardo
 */
public abstract class AbstractAction implements FlowTableInterface {

    /**
     * Representation of the different types of actions.
     */
    public enum ActionType {
        NULL(0),
        FORWARD_U(1),
        FORWARD_B(2),
        DROP(3),
        ASK(4),
        FUNCTION(5),
        SET(6),
        MATCH(7);

        private final byte value;
        private static final ActionType[] A_VALUES = ActionType.values();

        public static ActionType fromByte(final byte value) {
            return A_VALUES[value];
        }

        private ActionType(final int value) {
            this.value = (byte) value;
        }
    }

    protected final static int TYPE_INDEX = 0;
    protected byte[] action;

    /**
     * Constructor for the FlowTableAction object.
     *
     * @param actionType the type of action that will be created
     * @param size the size of the action
     */
    public AbstractAction(final ActionType actionType, final int size) {
        action = new byte[size + 1];
        setType(actionType);
    }

    /**
     * Constructor for the FlowTableAction object.
     *
     * @param value a byte array representing the action
     */
    public AbstractAction(final byte[] value) {
        this.action = value;
    }

    /**
     * Getter method to obtain the Type of AbstractAction. The possible types of
     * actions are SDN_WISE_FORWARD_U, SDN_WISE_FORWARD_B, SDN_WISE_DROP,
     * SDN_WISE_MODIFY, SDN_WISE_AGGREGATE, SDN_WISE_FORWARD_UP.
     *
     * @return value of the type action.
     */
    public final ActionType getType() {
        return ActionType.fromByte(action[TYPE_INDEX]);
    }

    @Override
    public final byte[] toByteArray() {
        return Arrays.copyOf(action, action.length);
    }

    @Override
    public final boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        AbstractAction other = (AbstractAction) obj;
        return Arrays.equals(other.action, action);
    }

    @Override
    public final int hashCode() {
        int hash = 5;
        hash = 61 * hash + Arrays.hashCode(this.action);
        return hash;
    }

    /**
     * Setter method to set the type of AbstractAction. The possible types of
     * actions are SDN_WISE_FORWARD_U, SDN_WISE_FORWARD_B, SDN_WISE_DROP,
     * SDN_WISE_MODIFY, SDN_WISE_AGGREGATE, SDN_WISE_FORWARD_UP.
     *
     * @param value will be set.
     * @return this AbstractAction.
     */
    final AbstractAction setType(final ActionType value) {
        action[TYPE_INDEX] = value.value;
        return this;
    }

    final AbstractAction setValue(final int index, final int value) {
        if (index < 0 || index >= action.length) {
            throw new ArrayIndexOutOfBoundsException("Index out of bound");
        } else {
            action[index + 1] = (byte) value;
        }
        return this;
    }

    final int getValue(final int index) {
        if (index < 0 || index >= action.length) {
            throw new ArrayIndexOutOfBoundsException("Index out of bound");
        } else {
            return action[index + 1];
        }
    }

    @Override
    public String toString() {
        return getType().name();
    }
}
