package pl.jakubgajewski.GieldaRowerowa.services;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;

@Service
@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
@Data
@NoArgsConstructor
public class UserService {
    private int id;
    private boolean isLogged;
}
