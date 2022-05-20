package TopologyApi;

import java.util.Collection;
import java.util.LinkedList;

import org.json.simple.JSONObject;

public class TopologyList {
    LinkedList<Topology> topologies = null;

    public TopologyList() {

    }

    public TopologyList(LinkedList<Topology> topologies) {
        this.topologies = topologies;
    }

    /**
     * add new topology to memory.
     * 
     * @param topology
     */
    public void add(Topology topology) {
        if (topologies == null) {
            topologies = new LinkedList<>();
        }
        topologies.add(topology);
    }

    /**
     * 
     * @return {@link LinkedList <{@link Component}>
     */
    public LinkedList<Topology> getCurrentTopologies() {
        return topologies;
    }

    public LinkedList<String> getCurrentTopologiesIDs() {
        LinkedList<String> ids = new LinkedList<>();
        for (Topology t : topologies) {
            ids.push(t.getId());
        }
        return ids;
    }

    /**
     * delete topology from memory by topology object
     * 
     * @param topology
     */
    public void deleteTopology(Topology topology) {
        topologies.remove(topology);
    }

    /**
     * delete topology from memory by topology ID
     * 
     * @param String
     */
    public void deleteTopologyByID(String topologyId) {
        for (Topology t : topologies) {
            if (t.getId().equals(topologyId)) {
                topologies.remove(t);
            }
        }
    }

    /**
     * return LinkedList of {@link Component} class.
     * 
     * @param topology
     * @return {@link LinkedList <{@link Component}>
     */
    public LinkedList<Component> getTopologyComponents(Topology topology) {
        return topology.getComponents();
    }

    /**
     * return LinkedList of {@link Component} class.
     * 
     * @param topology
     * @return {@link LinkedList <{@link Component}>
     */
    public LinkedList<Component> getTopologyComponents(String topologyId) {
        for (Topology topology : topologies) {
            if (topology.getId().equals(topologyId)) {
                return topology.getComponents();
            }
        }
        return null;
    }

    /**
     * return LinkedList of {@link Component} class of the components (devices) that
     * connected.
     * 
     * @param topology
     * @return {@link LinkedList <{@link Component}>
     */
    public LinkedList<Component> getDevicesWithNetlistNode(String topologyId, String netlistNodeID) {
        LinkedList<Component> connectedDevices = new LinkedList<>();
        LinkedList<Component> components = getTopologyComponents(topologyId);

        for (Component component : components) {
            Collection values = component.getNetlist().values();
            if (values.contains(netlistNodeID)) {
                connectedDevices.add(component);
            }
        }
        return connectedDevices;
    }

}
