@Service
public class PropertyService {

    private PropertyRepository repository;

    @Autowired
    public PropertyService(PropertyRepository repository) {
        this.repository = repository;
    }

    public List<Property> searchProperties(SearchFilter filters) {
        List<Property> properties = repository.findAll();
    
        // filtrar as propriedades de acordo com os filtros passados na busca
        if (filters.getType() != null) {
            properties = properties.stream()
                    .filter(p -> p.getType().equals(filters.getType()))
                    .collect(Collectors.toList());
        }
        if (filters.getNeighborhood() != null) {
            properties = properties.stream()
                    .filter(p -> p.getNeighborhood().equals(filters.getNeighborhood()))
                    .collect(Collectors.toList());
        }
        if (filters.getPrice() != null) {
            properties = properties.stream()
                    .filter(p -> p.getPrice() >= filters.getPrice())
                    .collect(Collectors.toList());
        }
        return properties;
    }
}