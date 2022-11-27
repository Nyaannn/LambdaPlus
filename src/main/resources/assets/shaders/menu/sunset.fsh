#extension GL_OES_standard_derivatives : enable

precision highp float;

uniform float time;
uniform vec2 mouse;
uniform vec2 resolution;

             const float r = .5;
             const float mg = 1.7320508;

                         const float PI_H = 1.570796326794896;
const float PI = 3.14159265358979;
const float PI_2 = 6.28318530717958;
                   const float PI_4 = 12.56637061435917;

                   const vec3 mulColor1 = vec3( .9, .2, 1. );
                                                           const vec3 mulColor2 = vec3( .1, .8, .2 );
                                                                                            const vec3 lineColor1 = vec3( .0, .95, .5 );
                                                                                                                        const vec3 lineColor2 = vec3( .1, .05, .9 );
                                                                                                                                                                   const vec3 bgColor = vec3( .2, .15, .5 );
                                                                                                                                                                                                     const vec3 glowColor = vec3( .1, .75, .55 );
                                                                                                                                                                                                                                  const vec3 frontColor = vec3( 1., 1., 1. );
                                                                                                                                                                                                                                                                         const vec3 frontColor2 = vec3( .01, .03, .05 );

                                                                                                                                                                                                                                                                                                           vec3 hex( in vec2 p, in float t, in float x, in float y, in float o ) {

                                                                                                                                                                                                                                                                                                                                                                                     float d;
                                                                                                                                                                                                                                                                                                                                                                                     if ( abs(p.x) > abs(p.y) * mg )
                                                                                                                                                                                                                                                                                                                                                                                                                   d += abs(p.x);
                                                                                                                                                                                                                                                                                                                                                                                     else
                                                                                                                                                                                                                                                                                                                                                                                     d += (abs(p.y) * mg + abs(p.x)) * .5;

                                                                                                                                                                                                                                                                                                                                                                                                                     vec3 c = lineColor1 * clamp(PI-t, 0., PI) + lineColor2 * min(t, PI);
                                                                                                                                                                                                                                                                                                                                                                                                                                                                 c += (1. - p.x) * r * 5. * mulColor1 + p.x * r * 5. * mulColor2;

                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                            float w = (y - o) * x + 1.;
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                     float wp = w * PI;

                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                     float q = r - d;

                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                           float m = 0.;
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                      m += 1. / abs(d - r) * .007 - .01;
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                      if (d < r) m -= q;
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                          vec3 res = c * clamp( m, 0., 9. );

                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                       float a = atan(p.y, p.x) + PI * (1.5 - cos(mod(min(t, wp), PI)));
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                           float s = mod(a, PI_2) / (PI_2);
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                     float f = (s - .5);

                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                      res *= clamp( 1. / (abs(f) + .05) * .007 - .015 * (.5 - cos(mod(min(t * 2., wp * 2.), PI_2))), 0., 8. ) * (w - 1.);

                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                        return res * ( 2.0 - ( cos(min(t / w, PI) * 2.) + 1. ));

                                                                                                                                                                                                                                                                                                                                                                                 }

                                                                                                                                                                                                     vec3 bg( in float d ) {

                                                                                                                                                                                                                               return bgColor * ( 4. - d ) * .09;

                                                                                                                                                                                                                           }

vec3 glow( in vec2 p ) {

                           float g = length( vec2( ( gl_FragCoord.y * 2. ) / min( resolution.x, resolution.y ), p.x ) );
                                                                                                                    return glowColor * max((1. / (g + 0.3) * .15), 0.);

                       }

vec3 front( in vec3 res, in vec2 p, in float t ) {

                                                     p *= 1. / (.97 + (1. - sin(min(t * .2 * PI, PI_H))) * .3);

                                                     float d;
                                                     if ( abs(p.x) > abs(p.y) * mg )
                                                                                   d += abs(p.x);
                                                     else
                                                     d += (abs(p.y) * mg + abs(p.x)) * .5;

                                                                                     if (t > 1.5) {

                                                                                                      float tq = clamp(((t - 1.5) * 4.5 + p.y * mg * r) * 1., 0., 1.);
                                                                                                                                                                   float v = clamp(((1. / abs(r - d) * .0015) - .2) * tq, 0., 1.);

                                                                                                                                                                                                                            if (d < r + .01) {

                                                                                                                                                                                                                                                 float i = clamp((r - d) * 500., 0., 1.);
                                                                                                                                                                                                                                                                                        if (t < 1.8)
                                                                                                                                                                                                                                                                                        res += clamp((t - 1.5) * 3., 0., 1.) * i;
                                                                                                                                                                                                                                                                                                                     else {

                                                                                                                                                                                                                                                                                                                              float a = clamp((1.8 - t) * 4. + p.x * 0.2 - p.y * .5 + 1., 0., 1.);
                                                                                                                                                                                                                                                                                                                                                                                           res = mix(res, mix(frontColor2, frontColor, a), i);

                                                                                                                                                                                                                                                                                                                          }

                                                                                                                                                                                                                                             }

                                                                                                                                if (t >= 1.8) {

                                                                                                                                                  v *= clamp(1.8 - t * .8, 0., 1.);

                                                                                                                                              }

                                                                                                      res += frontColor * v;
                                                                                                  }
                                                     return res;

                                                 }

void main( void ) {

                      vec3 res;

                      vec2 p = ( gl_FragCoord.xy * 2. - resolution.xy ) / min( resolution.x, resolution.y );

                      float t = mod(time * 1.2, 3.);

                                                 for (float i = 0.; i < 12.; i++) {
                                                                                      float ti = mod(time * 1.2 - i * 0.02, 3.);
                                                                                                                    vec2 pm = p * (1. - (1. + -ti * .25) * (i * .01));
                                                                                                                                                                   float t2 = t * t * .5;
                                                                                                                                                                                      float o = .02 * i * (.9 + t2 * .4);
                                                                                                                                                                                                           float s = sin(o);
                                                                                                                                                                                                                     float c = cos(o);
                                                                                                                                                                                                                                   res += hex(vec2((pm.x * c - pm.y * s) / (1. + i * 0.015), pm.y * c + pm.x * s), t2 * PI, .083, 12., i);
                                                                                  }
                      res += bg(length(p));
                      res += glow(p);
                      res = front(res, p, t);

                                 gl_FragColor = vec4( res, 1. );

                  }