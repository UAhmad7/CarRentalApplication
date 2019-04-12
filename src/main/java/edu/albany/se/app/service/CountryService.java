package edu.albany.se.app.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.albany.se.app.model.Country;
import edu.albany.se.app.repository.CountryRepository;

@Service
public class CountryService
{
    private CountryRepository countryRepository;
}
