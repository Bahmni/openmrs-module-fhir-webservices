package org.openmrs.module.fhirwebservices.resourceProvider;

import ca.uhn.fhir.rest.annotation.IdParam;
import ca.uhn.fhir.rest.annotation.Read;
import ca.uhn.fhir.rest.server.IResourceProvider;
import org.hl7.fhir.dstu3.model.IdType;
import org.hl7.fhir.dstu3.model.Location;
import org.hl7.fhir.instance.model.api.IBaseResource;
import org.openmrs.api.LocationService;
import org.openmrs.api.context.Context;
import org.openmrs.healthStandard.converter.fhir.FHIRConverter;
import org.openmrs.healthStandard.converter.fhir.FHIRConverterRegistry;

public class LocationResourceProvider implements IResourceProvider {
    public Class<? extends IBaseResource> getResourceType() {
        return Location.class;
    }

    @Read
    public Location getLocation(@IdParam IdType locationId) {
        LocationService locationService = Context.getLocationService();
        org.openmrs.Location openmrsLocation = locationService.getLocationByUuid(locationId.getIdPart());
        FHIRConverter<org.openmrs.Location, Location> converter = FHIRConverterRegistry.getInstance()
                .getConverterFor(org.openmrs.Location.class, Location.class);

        return converter.convert(openmrsLocation);
    }
}
