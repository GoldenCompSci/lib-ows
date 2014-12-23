
package net.opengis.fes.v20.impl;

import net.opengis.fes.v20.FilterCapabilities;
import net.opengis.fes.v20.ScalarCapabilities;
import net.opengis.fes.v20.SpatialCapabilities;
import net.opengis.fes.v20.TemporalCapabilities;


public class FilterCapabilitiesImpl implements FilterCapabilities
{
    static final long serialVersionUID = 1L;
    protected ScalarCapabilities scalarCapabilities;
    protected SpatialCapabilities spatialCapabilities;
    protected TemporalCapabilities temporalCapabilities;


    public FilterCapabilitiesImpl()
    {
    }


    @Override
    public ScalarCapabilities getScalarCapabilities()
    {
        return scalarCapabilities;
    }


    @Override
    public void setScalarCapabilities(ScalarCapabilities scalarCapabilities)
    {
        this.scalarCapabilities = scalarCapabilities;
    }


    @Override
    public SpatialCapabilities getSpatialCapabilities()
    {
        return spatialCapabilities;
    }


    @Override
    public void setSpatialCapabilities(SpatialCapabilities spatialCapabilities)
    {
        this.spatialCapabilities = spatialCapabilities;
    }


    @Override
    public TemporalCapabilities getTemporalCapabilities()
    {
        return temporalCapabilities;
    }


    @Override
    public void setTemporalCapabilities(TemporalCapabilities temporalCapabilities)
    {
        this.temporalCapabilities = temporalCapabilities;
    }
}