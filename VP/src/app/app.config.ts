import {
  ApplicationConfig,
  importProvidersFrom,
  provideZoneChangeDetection,
} from '@angular/core';
import { provideRouter } from '@angular/router';

import { routes } from './app.routes';
import { provideClientHydration } from '@angular/platform-browser';
import { provideAnimationsAsync } from '@angular/platform-browser/animations/async';
import {
  provideHttpClient,
  withFetch,
  withInterceptorsFromDi,
} from '@angular/common/http';
import { provideCharts, withDefaultRegisterables } from 'ng2-charts';
import { provideNativeDateAdapter } from '@angular/material/core';
import { JwtModule } from '@auth0/angular-jwt';

export function tokenGetter() {
  if (typeof window !== 'undefined') {
    return sessionStorage.getItem('token');
  }
  return null; 
}

export const appConfig: ApplicationConfig = {
  providers: [
    provideZoneChangeDetection({ eventCoalescing: true }),
    provideRouter(routes),
    provideClientHydration(),
    provideAnimationsAsync(),
    provideHttpClient(withInterceptorsFromDi(),withFetch()), 
    provideCharts(withDefaultRegisterables()),
    provideNativeDateAdapter(),
    importProvidersFrom(
      JwtModule.forRoot({
        config: {
          tokenGetter: tokenGetter,
          allowedDomains: ['localhost:8087'],
          disallowedRoutes: ['http://localhost:8087/login/forget'],
        },
      })
    ),
  ],
};
