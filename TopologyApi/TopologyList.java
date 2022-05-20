package TopologyApi;

import java.util.LinkedList;

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
        for (Topology t : topologies) {
            if (t.getId().equals(topologyId)) {
                return t.getComponents();
            }
        }
        return null;
    }

    // public getConnectedDevicesbyNetlist(String topology, String netlist){

    // }

}
